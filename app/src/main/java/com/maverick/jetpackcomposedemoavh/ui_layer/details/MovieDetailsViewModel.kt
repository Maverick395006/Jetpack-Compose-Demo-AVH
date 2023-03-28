package com.maverick.jetpackcomposedemoavh.ui_layer.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverick.jetpackcomposedemoavh.common.DataState
import com.maverick.jetpackcomposedemoavh.data_layer.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {

    val movieDetails = mutableStateOf(MovieDetailsStateHolder())

    init {
        movieDetails.value = MovieDetailsStateHolder(isLoading = true)
        viewModelScope.launch {
            savedStateHandle.getStateFlow("id", "0").collectLatest {
                getMovieDetails(it)
            }
        }
    }

    private fun getMovieDetails(id: String) = viewModelScope.launch {

        val result = movieRepository.getMovieDetails(id)

        when (result) {
            is DataState.Error -> {
                movieDetails.value = MovieDetailsStateHolder(error = result.message.toString())
            }
            is DataState.Success -> {
                movieDetails.value = MovieDetailsStateHolder(data = result.data)
            }
            else -> {

            }
        }
    }


}