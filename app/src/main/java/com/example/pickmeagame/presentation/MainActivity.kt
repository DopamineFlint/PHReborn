package com.example.pickmeagame.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pickmeagame.presentation.ui.theme.PickMeAGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickMeAGameTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    CenteredLogo()
}

@Composable
fun CenteredLogo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.background(Color.Red).fillMaxWidth().weight(1f)) {

        }

        Box(
            modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth()
                .weight(3f),
            contentAlignment = Alignment.Center
        ) {
            Text("Text", style = TextStyle(fontSize = 104.sp))
        }

        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxWidth()
                .weight(2f),
            contentAlignment = Alignment.Center
        ) {
            CustomSpinner()
        }
    }
}

@Composable
fun CustomSpinner() {
    CircularProgressIndicator()
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    PickMeAGameTheme {
        MyApp()
    }
}