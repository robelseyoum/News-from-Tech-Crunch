package com.robelseyoum3.techcrunch.network

import com.robelseyoum3.techcrunch.common.Constant
import com.robelseyoum3.techcrunch.model.TechCrunchPosts
import io.reactivex.Single
import retrofit2.http.GET

interface WebServices {

    @GET(Constant.ENDPOINT_URL)
    fun fetchPostsWebService(): Single<List<TechCrunchPosts>>
}