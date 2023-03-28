package com.maverick.jetpackcomposedemoavh.ui_layer

import com.maverick.jetpackcomposedemoavh.model_layer.Movie

data class MovieStateHolder(
    val isLoading: Boolean = false,
    val data: List<Movie>? = null,
    val error: String = "",
)