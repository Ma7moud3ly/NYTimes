package com.nytimes.api

import com.nytimes.data.NYTimes
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    //@GET("svc/mostpopular/v2/viewed/1.json?api-key=QXt0TfVDBiJHIAkNk4TE8OMCM5ygBRXq")
    //fun getAllArticles(): Call<List<Article>>

    @GET("svc/mostpopular/v2/viewed/1.json?api-key=QXt0TfVDBiJHIAkNk4TE8OMCM5ygBRXq")
    fun getResults(): Call<NYTimes>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.nytimes.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}