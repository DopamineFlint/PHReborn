package com.example.pickmeagame.presentation

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pickmeagame.presentation.ui.theme.PickMeAGameTheme
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
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

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val nums = arrayListOf(1, 2, 3, 4, 5)
    val events = arrayListOf("First", "Two", "Three", "Four", "Five")
    val secondEvents = arrayListOf("Three Five Six", "Nine Eleven", "Nine Inch Nails")
    val lazyListState: LazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(
        lazyListState = lazyListState
    )
    //var value by remember { mutableStateOf(layoutInfo.currentItem?.index) }

    LaunchedEffect(Unit) {
        snapshotFlow {
            lazyListState.isScrollInProgress }.collect {
            layoutInfo.currentItem?.index?.let { itemIndex ->
                Toast.makeText(context, "List $itemIndex", Toast.LENGTH_SHORT).show()
                if (itemIndex == 2) {

                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green)
    ) {
        val cntxt = LocalContext.current
        Row(modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth()
            .weight(1f)
        ) {
            BoxWithConstraints {
                LazyRow(
                    state = lazyListState,
                    flingBehavior = rememberSnapperFlingBehavior(lazyListState)
                ) {
                    itemsIndexed(nums) { index, num ->
                        Layout(
                            content = {
                                YearCard(currentYear = num)
                            },
                            measurePolicy = { measurables, constraints ->
                                val placeable = measurables.first().measure(constraints)
                                val maxWidthInPx = maxWidth.roundToPx()
                                val itemWidth = placeable.width
                                val startSpace =
                                    if (index == 0) (maxWidthInPx - itemWidth) / 2 else 0
                                val endSpace =
                                    if (index == nums.lastIndex) (maxWidthInPx - itemWidth) / 2 else 0
                                val width = startSpace + placeable.width + endSpace
                                layout(width, placeable.height) {
                                    val x = if (index == 0) startSpace else 0
                                    placeable.place(x, 0)
                                }
                            }
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .background(color = Color.Green)
                .fillMaxWidth()
                .weight(2f)
        ) {
            LazyColumn {
                items(events) { event ->
                    EventCard(event = event)
                }
            }
        }
    }
}

@Composable
fun CustomSpinner() {
    CircularProgressIndicator()
}

@Composable
fun YearCard(currentYear: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp)
            .clickable { },
        backgroundColor = Color.White,
    ) {
        Text(currentYear.toString(), modifier = Modifier.padding(50.dp))
    }
}

@Composable
fun EventCard(event: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { },
        backgroundColor = Color.White
    ) {
        Text(event, modifier = Modifier.padding(50.dp))
    }
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