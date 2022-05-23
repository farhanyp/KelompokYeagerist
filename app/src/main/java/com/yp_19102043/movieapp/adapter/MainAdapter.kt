package com.yp_19102043.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yp_19102043.movieapp.R
import com.yp_19102043.movieapp.model.MovieModel
import kotlinx.android.synthetic.main.adapter_main.view.*
import okhttp3.internal.notify

class MainAdapter(var movies: ArrayList<MovieModel>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount()= movies.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bind(movies: MovieModel){
            view.text_title.text = movies.title

        }
    }

    public fun setData(newMovies: List<MovieModel>){
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }
}