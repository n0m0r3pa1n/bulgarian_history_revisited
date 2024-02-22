package com.nmp90.bghistory.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nmp90.bghistory.compose.capitals.CapitalsList
import com.nmp90.bghistory.compose.navigation.NavigationItem

@Composable
fun Navigations(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Periods.route) {
        composable(NavigationItem.Periods.route) {
            CapitalsList()
        }
        composable(NavigationItem.Events.route) {
            CapitalsList()
        }
        composable(NavigationItem.Capitals.route) {
            CapitalsList()
        }
    }
}