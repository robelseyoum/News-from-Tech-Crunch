package com.robelseyoum3.techcrunch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robelseyoum3.techcrunch.repository.GetDataRepositoryImpl

class CrunchViewModelFactory (private val getDataRepositoryImpl: GetDataRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CrunchViewModel(getDataRepositoryImpl ) as T
    }
}