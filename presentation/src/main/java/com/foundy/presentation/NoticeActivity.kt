package com.foundy.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foundy.presentation.databinding.ActivityNoticeBinding

class NoticeActivity : AppCompatActivity() {

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


    }
}