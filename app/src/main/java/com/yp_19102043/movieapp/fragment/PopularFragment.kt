package com.yp_19102043.movieapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.yp_19102043.movieapp.R
import com.yp_19102043.movieapp.activity.DetailActivity
import com.yp_19102043.movieapp.adapter.MainAdapter
import com.yp_19102043.movieapp.model.Constant
import com.yp_19102043.movieapp.model.MovieModel
import com.yp_19102043.movieapp.model.MovieResponse
import com.yp_19102043.movieapp.retrofit.ApiService
import kotlinx.android.synthetic.main.fragment_popular.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularFragment : Fragment() {

    lateinit var v: View
    lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_popular, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecylceView()
    }

    override fun onStart() {
        super.onStart()
        getMoviePopular()
    }

    private fun setupRecylceView(){
        mainAdapter = MainAdapter(arrayListOf(), object : MainAdapter.OnAdapterListener{
            override fun onClick(movie: MovieModel) {
                startActivity(Intent(requireContext(), DetailActivity::class.java))
            }

        })
        v.list_movie.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mainAdapter

        }
    }

    fun getMoviePopular(){
        showLoading(true)
        ApiService().endpoint.getMoviePopular(Constant.API_KEY, 1)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    showLoading(false)
                    if(response.isSuccessful){
                        showMovie(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    showLoading(false)
                }

            })
    }

    fun showLoading(loading: Boolean){
        when(loading){
            true -> v.progress_movie.visibility = View.VISIBLE
            false -> v.progress_movie.visibility = View.GONE
        }
    }


    fun showMovie(response: MovieResponse){
        mainAdapter.setData(response.results)

    }

}