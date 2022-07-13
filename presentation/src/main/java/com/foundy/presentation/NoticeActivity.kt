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
import com.foundy.presentation.databinding.ActivityNoticeBinding
import com.google.android.material.snackbar.Snackbar
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
                viewModel.uiState.collect(::updateUi)
            }
        }
    }

    private fun updateUi(uiState: NoticeUiState) = with(binding) {
        progressBar.isVisible = uiState.isFetchingNotices
        text.text = uiState.notices.joinToString { it.toString() }
        if (uiState.error != null) {
            showSnackBar(uiState.error.message ?: "에러 발생")
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}