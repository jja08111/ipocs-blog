package com.foundy.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foundy.domain.model.User
import com.foundy.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeUserListState()
        initButton()
    }

    private fun observeUserListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userListState.collect {
                    when (it) {
                        is MainUiState.Loading -> onLoading()
                        is MainUiState.Success -> onSuccessGettingUsers(it.users)
                        is MainUiState.Error -> onErrorGettingUsers(it.exception)
                    }
                }
            }
        }
    }

    private fun initButton() {
        binding.noticeButton.setOnClickListener {
            startNoticeActivity()
        }
    }

    private fun onLoading() = with(binding) {
        progressBar.isVisible = true
    }

    private fun onSuccessGettingUsers(users: List<User>) = with(binding) {
        progressBar.isVisible = false
        text.text = users.joinToString { users.toString() }
    }

    private fun onErrorGettingUsers(e: Exception) = with(binding) {
        progressBar.isVisible = false
        error.text = e.message
    }

    private fun startNoticeActivity() {
        val intent = NoticeActivity.getIntent(this)
        startActivity(intent)
    }
}