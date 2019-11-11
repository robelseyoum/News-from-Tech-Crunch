package com.robelseyoum3.techcrunch.repository

import com.robelseyoum3.techcrunch.model.TechCrunchPosts
import io.reactivex.Single

interface Repository {
    fun getCrunchRepositoriesMethod(): Single<List<TechCrunchPosts>>
}