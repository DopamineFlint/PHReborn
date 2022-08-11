package com.example.pickmeagame.presentation

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pickmeagame.presentation.ui.theme.PickMeAGameTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickMeAGameTheme {
                //MyApp()
                Navigation()
            }
        }
    }
}

/*@Composable
fun MyApp() {
    CenteredLogo()
}*/

@Composable
fun CenteredLogo(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("main_screen")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth()
            .weight(1f)) {

        }

        Box(
            modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth()
                .weight(3f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Text",
                style = TextStyle(fontSize = 104.sp),
                modifier = Modifier.scale(scale.value)
            )
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
fun MainScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Welcome to MainScreen")
    }
}

@Composable
fun CustomSpinner() {
    CircularProgressIndicator()
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            CenteredLogo(navController = navController)
        }
        composable("main_screen") {
            MainScreen()
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    PickMeAGameTheme {
        //MyApp()
        Navigation()
    }
}