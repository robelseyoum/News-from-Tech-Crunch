package com.robelseyoum3.techcrunch.di

import com.robelseyoum3.techcrunch.repository.GetDataRepository
import com.robelseyoum3.techcrunch.viewmodel.CrunchViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(getDataRepository: GetDataRepository): CrunchViewModelFactory{
        return CrunchViewModelFactory(getDataRepository)
    }


}