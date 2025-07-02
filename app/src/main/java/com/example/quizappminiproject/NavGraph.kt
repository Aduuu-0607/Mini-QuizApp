package com.example.quizappminiproject

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizappminiproject.Signup.AuthScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        composable("auth") {
            AuthScreen {
                navController.navigate("home") {
                    popUpTo("auth") { inclusive = true }
                }
            }
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("result/{score}", arguments = listOf(navArgument("score") { type = NavType.IntType })) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            Score(score = score, navController)
        }
        composable("quiz/{score}", arguments = listOf(navArgument("score") { type = NavType.IntType })) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            Score(score = score, navController)
        }
        composable("bottom_nav_home") {
            BottomNav(navController = navController)
        }
        composable("phpQuizscreen") {
            PHPQuizScreen(navController)
        }
        composable("aiQuizscreen") {
            AIQuizScreen(navController)
        }
        composable("androidQuizscreen") {
            AndroidQuizScreen(navController)
        }

    }
}
