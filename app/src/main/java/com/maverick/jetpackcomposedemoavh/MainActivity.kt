package com.maverick.jetpackcomposedemoavh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.maverick.jetpackcomposedemoavh.navigation.MovieNavigation
import com.maverick.jetpackcomposedemoavh.ui.theme.JetpackComposeDemoAVHTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoAVHTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                MovieNavigation(navController)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    JetpackComposeDemoAVHTheme {

    }
}