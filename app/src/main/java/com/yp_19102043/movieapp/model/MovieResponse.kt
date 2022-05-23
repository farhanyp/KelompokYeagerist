package com.yp_19102043.movieapp.model

data class MovieResponse (
    val total_pages: Int,
    val results: List<MovieModel>,
)