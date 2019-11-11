package com.robelseyoum3.techcrunch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robelseyoum3.techcrunch.repository.GetDataRepository

class CrunchViewModelFactory (private val getDataRepository: GetDataRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CrunchViewModel(getDataRepository ) as T
    }
}