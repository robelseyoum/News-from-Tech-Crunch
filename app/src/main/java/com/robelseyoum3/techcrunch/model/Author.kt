package com.robelseyoum3.techcrunch.model


import com.google.gson.annotations.SerializedName

data class Author(

    @SerializedName("embeddable")
    val embeddable: Boolean,

    @SerializedName("href")
    val href: String
)