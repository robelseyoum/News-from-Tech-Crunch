package com.robelseyoum3.techcrunch.di

import com.robelseyoum3.techcrunch.view.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(RepositoryModule::class, NetworkModule::class))

interface AppComponent {
    fun inject(mainActivity: MainActivity)
}