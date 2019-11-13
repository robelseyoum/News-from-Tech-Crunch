package com.robelseyoum3.techcrunch.model


import com.google.gson.annotations.SerializedName

data class TechCrunchPosts(

    @SerializedName("author")
    val author: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("jetpack_featured_media_url")
    val jetpackFeaturedMediaUrl: String,

    @SerializedName("title")
    val title: Title

)