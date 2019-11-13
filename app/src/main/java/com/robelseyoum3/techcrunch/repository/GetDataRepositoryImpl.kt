package com.robelseyoum3.techcrunch.repository

import com.robelseyoum3.techcrunch.model.TechCrunchPosts
import com.robelseyoum3.techcrunch.network.WebServices
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetDataRepositoryImpl @Inject constructor(private val webServices: WebServices) : Repository {

    override fun getCrunchRepositoriesMethod(): Single<List<TechCrunchPosts>> {
        return webServices
            .fetchPostsWebService()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
