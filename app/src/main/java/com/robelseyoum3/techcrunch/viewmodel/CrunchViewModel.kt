package com.robelseyoum3.techcrunch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robelseyoum3.techcrunch.model.TechCrunchPosts
import com.robelseyoum3.techcrunch.repository.GetDataRepository
import io.reactivex.disposables.CompositeDisposable

class CrunchViewModel (val getDataRepository: GetDataRepository): ViewModel() {

    private var allNewsMutableData: MutableLiveData<List<TechCrunchPosts>> = MutableLiveData()

    private var progressbarMutableData:  MutableLiveData<Boolean> = MutableLiveData()

    private val errorMutuableData: MutableLiveData<Boolean> = MutableLiveData()

    private var compositeDisposable = CompositeDisposable() //we can add several observable

    fun getAllNewsData(){

        compositeDisposable.add(

            getDataRepository.getCrunchRepositoriesMethod()
                .doOnSubscribe { progressbarMutableData.postValue(true) }
                .doOnError { progressbarMutableData.value = false }
                .subscribe(
                    { news ->
                        allNewsMutableData.value = news
                        progressbarMutableData.value = false
                    },
                    {
                        errorMutuableData.value = false
                    }
                )
        )
    }

    fun retunAllNewsResult() = allNewsMutableData

    fun retunError() = errorMutuableData

    fun returnProgerssBarValue() = progressbarMutableData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
