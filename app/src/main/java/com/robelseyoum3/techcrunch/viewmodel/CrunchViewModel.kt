package com.robelseyoum3.techcrunch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robelseyoum3.techcrunch.model.TechCrunchPosts
import com.robelseyoum3.techcrunch.repository.GetDataRepositoryImpl
import io.reactivex.disposables.CompositeDisposable

class CrunchViewModel (private val getDataRepositoryImpl: GetDataRepositoryImpl): ViewModel() {

    private var allNewsMutableData: MutableLiveData<List<TechCrunchPosts>> = MutableLiveData()

    private var progressbarMutableData:  MutableLiveData<Boolean> = MutableLiveData()

    private val errorMutableData: MutableLiveData<Boolean> = MutableLiveData()

    private var compositeDisposable = CompositeDisposable() //we can add several observable

    fun getAllNewsData() {

        compositeDisposable.add(

            getDataRepositoryImpl.getCrunchRepositoriesMethod()
                .doOnSubscribe { progressbarMutableData.postValue(true) }
                .doOnError { progressbarMutableData.value = false }
                .subscribe (
                    { news ->
                        allNewsMutableData.value = news
                        progressbarMutableData.value = false
                    },
                    {
                        errorMutableData.value = false
                    }
                )
        )
    }

    fun returnAllNewsResult() = allNewsMutableData

    fun returnError() = errorMutableData

    fun returnProgressBarValue() = progressbarMutableData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
