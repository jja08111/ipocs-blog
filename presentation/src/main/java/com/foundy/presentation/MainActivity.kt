package com.foundy.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
                viewModel.uiState.collect(::updateUi)
            }
        }
    }

    private fun initButton() {
        binding.noticeButton.setOnClickListener {
            startNoticeActivity()
        }
    }

    private fun updateUi(uiState: MainUiState) = with(binding) {
        progressBar.isVisible = uiState.isFetchingUsers
        text.text = uiState.users.joinToString { it.toString() }
        error.text = uiState.error?.message ?: ""
    }

    private fun startNoticeActivity() {
        val intent = NoticeActivity.getIntent(this)
        startActivity(intent)
    }
}