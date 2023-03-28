package com.maverick.jetpackcomposedemoavh.ui_layer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.maverick.jetpackcomposedemoavh.model_layer.Movie
import com.maverick.jetpackcomposedemoavh.navigation.MovieNavigationItem

@Composable
fun MovieListScreen(
    navController: NavController,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

    val result = movieViewModel.movieList.value

    if (result.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (result.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = result.error)
        }
    }

    result.data?.let {
        LazyColumn {
            items(result.data) {
                MovieItem(it) {
                    navController.navigate(MovieNavigationItem.MovieDetails.route+"/$it")
                }
            }
        }
    }

}

@Composable
fun MovieItem(movie: Movie, onClick: (String) -> Unit) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(4.dp)
            .clickable {
                onClick.invoke(movie.id.toString())
            },
        contentScale = ContentScale.Crop
    )
}