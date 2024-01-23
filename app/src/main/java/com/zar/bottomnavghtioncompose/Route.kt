package com.zar.bottomnavghtioncompose

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object SplashScreen : Route(route = "splashScreen")
    object LoginScreen : Route(route = "loginScreen")
}