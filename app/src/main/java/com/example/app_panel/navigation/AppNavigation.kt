package com.example.app_panel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app_panel.screens.SplashScreen

@Composable
fun AppNavegation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "splash"){
        composable("splash"){
            SplashScreen(navController)
        }
        composable("connect"){

        }

    }
}