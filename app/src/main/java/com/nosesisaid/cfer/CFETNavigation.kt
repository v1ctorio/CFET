package com.nosesisaid.cfer

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nosesisaid.cfer.login.SingInScreen
import com.nosesisaid.cfer.management.ManageEmailsScreen
import com.nosesisaid.cfer.management.ManageRoutesScreen

@Composable
fun CFETNavigation(
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            SingInScreen(navController = navController)
        }
        composable("manageEmails") {
            ManageEmailsScreen(navController = navController)
        }
        composable("manageRoutes") {
            ManageRoutesScreen(navController = navController)
        }
    }
}

