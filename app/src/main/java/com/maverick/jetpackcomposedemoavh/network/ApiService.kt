package com.maverick.jetpackcomposedemoavh.network

import com.maverick.jetpackcomposedemoavh.model_layer.MovieListResponse
import com.maverick.jetpackcomposedemoavh.model_layer.details.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //https://api.themoviedb.org/  3/movie/popular?api_key=51462eda328121849783066b83b9f3bb

    @GET("3/movie/popular")
    suspend fun getMovieList(@Query("api_key") apikey: String): MovieListResponse


    //https://api.themoviedb.org/  3/movie/76600?api_key=51462eda328121849783066b83b9f3bb

    @GET("3/movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: String,
        @Query("api_key") apikey: String
    ): MovieDetails

}