package com.nytimes.ui.articles

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nytimes.data.Article
import com.nytimes.data.NYTimes
import com.nytimes.repository.ArticlesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesViewModel constructor(private val repository: ArticlesRepository) : ViewModel() {

    val articles = MutableLiveData<List<Article>>()
    val errorMessage = MutableLiveData<String>()

    fun getResults() {
        val response = repository.getResults()
        response?.enqueue(object : Callback<NYTimes> {
            override fun onResponse(call: Call<NYTimes>, response: Response<NYTimes>) {
                response.body()?.let {
                    articles.postValue(it.articles)
                }
            }

            override fun onFailure(call: Call<NYTimes>, t: Throwable) {
                Log.e("HINT", "" + t.message)
                errorMessage.postValue(t.message)
            }
        })
    }

}