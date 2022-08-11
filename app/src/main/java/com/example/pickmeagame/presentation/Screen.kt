package com.example.pickmeagame.presentation

sealed class Screen(val route: String) {
    object FirstScreen: Screen("first_screen")
    object SecondScreen: Screen("second_screen")
}