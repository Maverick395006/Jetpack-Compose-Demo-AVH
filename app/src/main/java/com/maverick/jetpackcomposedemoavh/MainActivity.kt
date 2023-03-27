package com.maverick.jetpackcomposedemoavh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.maverick.jetpackcomposedemoavh.ui.theme.JetpackComposeDemoAVHTheme
import com.maverick.jetpackcomposedemoavh.ui.view.TipCalculatorScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoAVHTheme {
                // A surface container using the 'background' color from the theme
                TipCalculatorScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    JetpackComposeDemoAVHTheme {
        TipCalculatorScreen()
    }
}