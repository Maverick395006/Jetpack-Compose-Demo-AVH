package com.maverick.jetpackcomposedemoavh.data_layer

import com.maverick.jetpackcomposedemoavh.network.ApiService

class MovieDataSource(private val apiService: ApiService) {

    suspend fun getMovieList() =
        apiService.getMovieList(apikey = "51462eda328121849783066b83b9f3bb")

    suspend fun getMovieDetails(id: String) =
        apiService.getMovieDetails(id = id, apikey = "51462eda328121849783066b83b9f3bb")

}