package com.maverick.jetpackcomposedemoavh.ui_layer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverick.jetpackcomposedemoavh.common.DataState
import com.maverick.jetpackcomposedemoavh.data_layer.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    val movieList = mutableStateOf(MovieStateHolder())

    init {
        movieList.value = MovieStateHolder(isLoading = true)
        getMovieList()
    }

    fun getMovieList() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = movieRepository.getMovieList()) {
            is DataState.Success -> {
                movieList.value = MovieStateHolder(data = result.data)
            }
            is DataState.Error -> {
                movieList.value = MovieStateHolder(error = result.message.toString())
            }
            else -> {

            }
        }
    }

}