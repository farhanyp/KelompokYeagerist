package com.yp_19102043.movieapp.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yp_19102043.movieapp.R
import com.yp_19102043.movieapp.adapter.MainAdapter
import com.yp_19102043.movieapp.databinding.ActivityMainBinding
import com.yp_19102043.movieapp.model.Constant
import com.yp_19102043.movieapp.model.MovieResponse
import com.yp_19102043.movieapp.retrofit.ApiService
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG: String = ""
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupvView()
        setupRecylceView()
        }

    override fun onStart() {
        super.onStart()
        getMovie()
    }

    private fun setupvView(){
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setupRecylceView(){
        mainAdapter = MainAdapter(arrayListOf())
        list_movie.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mainAdapter

        }
    }

    fun getMovie(){
        showLoading(true)
        ApiService().endpoint.getMovieNowPlaying(Constant.API_KEY, 1)
            .enqueue(object : Callback<MovieResponse>{
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
                    Log.d(TAG, "errorResponse: $t")
                    showLoading(false)
                }

            })
    }

    fun showLoading(loading: Boolean){
        when(loading){
            true -> progress_movie.visibility = View.VISIBLE
            false -> progress_movie.visibility = View.GONE
        }
    }


    fun showMovie(response: MovieResponse){
//        Log.d(TAG, "responsMovie: $response")
//        Log.d(TAG, "responsMovie: ${response.total_pages}")
//
//        for (movie in response.results){
//            Log.d(TAG, "Movie Title: ${movie.title}")
//        }
        mainAdapter.setData(response.results)

    }

    fun showMessage(msg: String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }


    }