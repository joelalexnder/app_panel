package com.example.app_panel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app_panel.screens.ConnectScreen
import com.example.app_panel.screens.SplashScreen
import com.example.app_panel.screens.CreateRouteScreen


@Composable
fun AppNavegation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "splash"){
        composable("splash"){
            SplashScreen(navController)
        }
        composable("connect"){
            ConnectScreen(navController)

        }
        composable("createRoute") {
            CreateRouteScreen { routeName, ida, vuelta, isMinutes ->
                // Aquí irás integrando la lógica real con ESP32
                navController.popBackStack()
            }
        }

    }
}