package com.maverick.jetpackcomposedemoavh.data_layer

import com.maverick.jetpackcomposedemoavh.common.DataState
import com.maverick.jetpackcomposedemoavh.model_layer.Movie

class MovieRepository(private val movieDataSource: MovieDataSource) {

    suspend fun getMovieList(): DataState<List<Movie>> {
        return try {
            DataState.Success(data = movieDataSource.getMovieList().results)
        } catch (e: Exception) {
            DataState.Error(message = e.message.toString())
        }
    }

}