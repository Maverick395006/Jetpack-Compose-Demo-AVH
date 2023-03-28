package com.maverick.jetpackcomposedemoavh.ui_layer.details

import com.maverick.jetpackcomposedemoavh.model_layer.details.MovieDetails

data class MovieDetailsStateHolder(
    val isLoading: Boolean = false,
    val data: MovieDetails? = null,
    val error: String = "",
)