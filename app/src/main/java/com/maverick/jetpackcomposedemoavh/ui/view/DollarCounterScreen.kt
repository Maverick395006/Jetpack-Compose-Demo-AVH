package com.maverick.jetpackcomposedemoavh.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maverick.jetpackcomposedemoavh.ui.theme.JetpackComposeDemoAVHTheme

/**
 * State Hoisting
 */

@Composable
fun DollarCounterScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val counter = remember {
            mutableStateOf(1)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(text = "$${counter.value * 100}", style = MaterialTheme.typography.h6)

            CustomButton {
                counter.value++
            }

        }
    }
}

@Composable
fun CustomButton(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clickable {
                onClick.invoke()
            },
        shape = CircleShape,
        backgroundColor = Color.Yellow,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "TAP", style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DollarCounterScreenPreview() {
    JetpackComposeDemoAVHTheme {
        DollarCounterScreen()
    }
}