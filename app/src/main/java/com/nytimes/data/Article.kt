package com.nytimes.data

import com.google.gson.annotations.SerializedName

class Article(
    @SerializedName("title")
    val title: String,

    @SerializedName("byline")
    val author: String,

    @SerializedName("source")
    val source: String,

    @SerializedName("published_date")
    val date: String,

    @SerializedName("media")
    val media: List<Media>,

    @SerializedName("url")
    val url: String
) {
    fun thumbnail(): String? {
        return try {
            media[0].mediaMetadata[0].url
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

data class Media(
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>
)

data class MediaMetadata(
    val url: String
)
