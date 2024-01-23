package com.zar.bottomnavghtioncompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zar.bottomnavghtioncompose.component.botttomnava.BottomNavaghtion


@Composable
fun SetupNavigator() {


    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value


    Scaffold(
        bottomBar = {
            BottomNavaghtion()
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.SplashScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.SplashScreen.route) {

            }

            composable(route = Route.LoginScreen.route) {

            }

        }
    }

}