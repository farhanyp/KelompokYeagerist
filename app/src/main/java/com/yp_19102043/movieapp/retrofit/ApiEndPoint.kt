package com.yp_19102043.movieapp.retrofit

import com.yp_19102043.movieapp.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

}