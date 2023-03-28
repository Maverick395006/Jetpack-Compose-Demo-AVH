package com.maverick.jetpackcomposedemoavh.data_layer

import com.maverick.jetpackcomposedemoavh.common.DataState
import com.maverick.jetpackcomposedemoavh.model_layer.Movie
import com.maverick.jetpackcomposedemoavh.model_layer.details.MovieDetails

class MovieRepository(private val movieDataSource: MovieDataSource) {

    suspend fun getMovieList(): DataState<List<Movie>> {
        return try {
            DataState.Success(data = movieDataSource.getMovieList().results)
        } catch (e: Exception) {
            DataState.Error(message = e.message.toString())
        }
    }

    suspend fun getMovieDetails(id: String): DataState<MovieDetails>{
        return try {
            DataState.Success(data = movieDataSource.getMovieDetails(id))
        } catch (e: Exception) {
            DataState.Error(message = e.message.toString())
        }
    }

}