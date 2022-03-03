package com.nytimes.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nytimes.ui.articles.ArticlesViewModel
import com.nytimes.repository.ArticlesRepository


class ViewModelFactory constructor(private val repository: ArticlesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ArticlesViewModel::class.java)) {
            ArticlesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}