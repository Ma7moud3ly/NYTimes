package com.nytimes.repository

import com.nytimes.api.RetrofitService

class ArticlesRepository(private val retrofitService: RetrofitService?) {
    fun getResults() = retrofitService?.getResults()
}