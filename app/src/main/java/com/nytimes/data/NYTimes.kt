package com.nytimes.data

import com.google.gson.annotations.SerializedName

data class NYTimes(
    @SerializedName("status")
    val status: String,

    @SerializedName("copyright")
    val copyright: String,

    @SerializedName("num_results")
    val count: Int,

    @SerializedName("results")
    val articles: List<Article>,
)