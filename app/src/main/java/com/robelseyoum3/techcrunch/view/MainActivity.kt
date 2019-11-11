package com.robelseyoum3.techcrunch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.robelseyoum3.techcrunch.R
import com.robelseyoum3.techcrunch.di.DaggerAppComponent
import com.robelseyoum3.techcrunch.di.NetworkModule
import com.robelseyoum3.techcrunch.di.RepositoryModule
import com.robelseyoum3.techcrunch.model.TechCrunchPosts
import com.robelseyoum3.techcrunch.viewmodel.CrunchViewModel
import com.robelseyoum3.techcrunch.viewmodel.CrunchViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var crunchViewModelFactory: CrunchViewModelFactory
    lateinit var viewModel: CrunchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDependencies()

        viewModel = ViewModelProviders.of(this, crunchViewModelFactory).get(CrunchViewModel::class.java)
        viewModel.getAllNewsData()

        viewModel.retunAllNewsResult().observe(this, object : Observer<List<TechCrunchPosts>>{
            override fun onChanged(t: List<TechCrunchPosts>) {
                allDataAdapter(t)
            }
        })

        viewModel.retunError().observe(this, Observer {
            if(it == true){
                progress_id_main.visibility = View.VISIBLE
            } else {
                progress_id_main.visibility = View.GONE
            }
        })


    }

    private fun allDataAdapter(t: List<TechCrunchPosts>) {

        val adaptor = NewsAdaptor(t)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = adaptor

    }

    private fun getDependencies() {
        DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .build()
            .inject(this)
    }
}
