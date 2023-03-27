package com.maverick.jetpackcomposedemoavh

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maverick.jetpackcomposedemoavh.ui.theme.JetpackComposeDemoAVHTheme

/**
 * State Management
 */

@Composable
fun StateManagement() {

    val username = remember {
        mutableStateOf("")
    }

    TextField(modifier = Modifier.fillMaxWidth(), value = username.value, onValueChange = {
        username.value = it
    })

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StateManagementPreview() {
    JetpackComposeDemoAVHTheme {
        StateManagement()
    }
}