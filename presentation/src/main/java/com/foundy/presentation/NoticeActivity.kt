package com.foundy.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foundy.domain.model.Notice
import com.foundy.presentation.databinding.ActivityNoticeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoticeActivity : AppCompatActivity() {

    private val viewModel: NoticeViewModel by viewModels()

    private var _binding: ActivityNoticeBinding? = null
    private val binding: ActivityNoticeBinding get() = requireNotNull(_binding)

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, NoticeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeNoticeListState()
    }

    private fun observeNoticeListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.noticeListState.collect {
                    when (it) {
                        is NoticeUiState.Loading -> onLoading()
                        is NoticeUiState.Success -> onSuccessGettingUsers(it.notices)
                        is NoticeUiState.Error -> onErrorGettingUsers(it.exception)
                    }
                }
            }
        }
    }

    private fun onLoading() = with(binding) {
        progressBar.isVisible = true
    }

    private fun onSuccessGettingUsers(notices: List<Notice>) = with(binding) {
        progressBar.isVisible = false
        text.text = notices.joinToString { notices.toString() }
    }

    private fun onErrorGettingUsers(e: Exception) = with(binding) {
        progressBar.isVisible = false
        error.text = e.message
    }
}