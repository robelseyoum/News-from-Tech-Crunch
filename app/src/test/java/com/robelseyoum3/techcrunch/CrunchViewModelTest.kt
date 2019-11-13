package com.robelseyoum3.techcrunch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.robelseyoum3.techcrunch.model.TechCrunchPosts
import com.robelseyoum3.techcrunch.model.Title
import com.robelseyoum3.techcrunch.repository.GetDataRepositoryImpl
import com.robelseyoum3.techcrunch.viewmodel.CrunchViewModel
import io.mockk.MockKAnnotations
import io.reactivex.Observer
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException


@RunWith(MockitoJUnitRunner::class)
class CrunchViewModelTest {


    @Rule
    @JvmField
    var testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getDataRepositoryImpl: GetDataRepositoryImpl

    lateinit var crunchViewModel: CrunchViewModel

    private val allNewsMutableData: androidx.lifecycle.Observer<List<TechCrunchPosts>> = mock()
    private val errorMutableData: androidx.lifecycle.Observer<Boolean> = mock()
    private val progressbarMutableData: androidx.lifecycle.Observer<Boolean> = mock()


    @Before
    fun setup() {

        crunchViewModel = CrunchViewModel(getDataRepositoryImpl)

        crunchViewModel.returnAllNewsResult().observeForever(allNewsMutableData)
        crunchViewModel.returnProgressBarValue().observeForever(progressbarMutableData)
        crunchViewModel.returnError().observeForever(errorMutableData)

    }

    private val title = Title("he Garage is a new blockchain-focused incubator based in Paris")
    private val jetpack_featured_media_url = "https://techcrunch.com/wp-content/uploads/2019/11/The-Garage.jpg"
    private val author = 7636871
    private val data = "2019-11-13T01:10:35"

    val expectedNews = mutableListOf<TechCrunchPosts>(
        TechCrunchPosts(7636871,  "2019-11-13T01:10:35",  "https://techcrunch.com/wp-content/uploads/2019/11/The-Garage.jpg",
            title))



    @Test
    fun getNews_with_success(){
        val news = mutableListOf(TechCrunchPosts(author, data, jetpack_featured_media_url, title))

        `when`(getDataRepositoryImpl.getCrunchRepositoriesMethod()).thenReturn(Single.just(news))

        crunchViewModel.getAllNewsData()

        verify(allNewsMutableData, times(1)).onChanged(news)
        verify(progressbarMutableData, times(1)).onChanged(true)
        verify(errorMutableData, times(0)).onChanged(false)
    }

    @Test
    fun getNews_ReturnError(){

        val error = "Error Message"

        `when`(getDataRepositoryImpl.getCrunchRepositoriesMethod()).thenReturn(Single.error(RuntimeException(error)))

        crunchViewModel.getAllNewsData()

        verify(allNewsMutableData, times(1)).onChanged(ArgumentMatchers.anyList())

        verify(progressbarMutableData, times(1)).onChanged(true)
        verify(progressbarMutableData, times(1)).onChanged(false)
        verify(errorMutableData, times(0)).onChanged(true)

    }


}