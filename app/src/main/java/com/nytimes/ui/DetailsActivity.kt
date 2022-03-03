package com.nytimes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nytimes.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {


    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {
            val url = it?.getStringExtra("article_url")
            url?.let {
                binding.webView.loadUrl(url)
            }
        }
    }
}