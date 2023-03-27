package com.maverick.jetpackcomposedemoavh

import android.os.Bundle
import android.text.InputFilter.AllCaps
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maverick.jetpackcomposedemoavh.ui.model.Project
import com.maverick.jetpackcomposedemoavh.ui.theme.JetpackComposeDemoAVHTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoAVHTheme {
                // A surface container using the 'background' color from the theme
                Portfolio()
            }
        }
    }
}

@Composable
fun Portfolio() {
    Surface(
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)

    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.user_image),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Divider(modifier = Modifier.padding(top = 24.dp, bottom = 20.dp))

            Text(
                text = "Maverick Universe",
                style = TextStyle(color = Color.Black),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Native Android Developer",
                style = MaterialTheme.typography.body2,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "/maverick7",
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.h6
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_github),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color.Black
                )
                Text(
                    text = "/maverick395006",
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.h6
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    //TODO
                },
                modifier = Modifier
                    .scale(1.5f, 1.5f)
                    .padding(12.dp)
            ) {
                Text(text = "My Projects")
            }

            LazyColumn(Modifier.padding(vertical = 8.dp)) {
                items(getProjectList()) {
                    ProjectItem(it)
                }
            }

        }

    }
}

@Composable
fun ProjectItem(project: Project) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_profile),
            contentDescription = null,
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(text = project.name, style = MaterialTheme.typography.h6)
            Text(text = project.name, style = MaterialTheme.typography.body1)
        }
    }
}

fun getProjectList() = listOf(
    Project("Social Media App", "Connect with your friends"),
    Project("Media Player App", "Listen music endlessly"),
    Project("Gaming Media ", "God of war Ragnarok lover"),
    Project("Social Media App", "Connect with your friends"),
    Project("Media Player App", "Listen music endlessly"),
    Project("Gaming Media ", "God of war Ragnarok lover")
)

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetpackComposeDemoAVHTheme {
        Portfolio()
    }
}