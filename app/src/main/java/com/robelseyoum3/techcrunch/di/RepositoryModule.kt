package com.robelseyoum3.techcrunch.di

import com.robelseyoum3.techcrunch.repository.GetDataRepositoryImpl
import com.robelseyoum3.techcrunch.viewmodel.CrunchViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(getDataRepositoryImpl: GetDataRepositoryImpl): CrunchViewModelFactory{
        return CrunchViewModelFactory(getDataRepositoryImpl)
    }


}