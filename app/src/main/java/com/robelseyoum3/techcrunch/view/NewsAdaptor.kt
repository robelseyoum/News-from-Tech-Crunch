package com.robelseyoum3.techcrunch.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.techcrunch.R
import com.robelseyoum3.techcrunch.model.TechCrunchPosts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_rows.view.*


class NewsAdaptor (private val techCrunchPosts: List<TechCrunchPosts>): RecyclerView.Adapter<NewsAdaptor.NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdaptor.NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_rows, parent, false))
    }

    override fun getItemCount(): Int {
        return techCrunchPosts.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.newsPostDate.text = techCrunchPosts[position].date
        holder.newsPostTitle.text = techCrunchPosts[position].title.rendered

        Picasso.get().load(techCrunchPosts[position].jetpackFeaturedMediaUrl).into(holder.newsPostImage)
    }

    class NewsViewHolder (view: View): RecyclerView.ViewHolder(view){

        val newsPostDate: TextView = view.tvNewsDate
        val newsPostTitle: TextView = view.tvNewsTitle
        val newsPostImage: ImageView = view.imgNews
    }


}

