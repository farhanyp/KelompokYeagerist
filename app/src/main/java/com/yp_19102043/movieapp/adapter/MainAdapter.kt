package com.yp_19102043.movieapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yp_19102043.movieapp.R
import com.yp_19102043.movieapp.model.Constant
import com.yp_19102043.movieapp.model.MovieModel
import kotlinx.android.synthetic.main.adapter_main.view.*
import okhttp3.internal.notify
import retrofit2.http.Tag

class MainAdapter(var movies: ArrayList<MovieModel>, var listener:OnAdapterListener): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val TAG: String = "MainAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind( movie )
        val posterPath = Constant.POSTER_PATH+movie.poster_path
        Picasso.get()
            .load(posterPath)
            .placeholder(R.drawable.placeholder_potrait)
            .error(R.drawable.placeholder_potrait)
            .into(holder.view.image_poster)

        holder.view.image_poster.setOnClickListener {
            listener.onClick(movie)
        }
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

    interface OnAdapterListener{
        fun onClick (movie: MovieModel)
    }
}