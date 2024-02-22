package com.nmp90.bghistory.compose.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nmp90.bghistory.compose.capitals.CapitalsList
import com.nmp90.bghistory.compose.events.EventsNavHost
import com.nmp90.bghistory.compose.navigation.NavigationItem.PeriodsNavGraph
import com.nmp90.bghistory.compose.topics.TopicsListNavHost

@Composable
fun Navigations(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = PeriodsNavGraph.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        navigation(
            startDestination = PeriodsNavGraph.requireStartDestination(),
            route = PeriodsNavGraph.route
        ) {
            composable(route = PeriodsNavGraph.Periods.route) {
                TopicsListNavHost(navController = navController)
            }
            composable(
                route = PeriodsNavGraph.Events.route,
                arguments = PeriodsNavGraph.Events.arguments
            ) { backStack ->
                val eventId = backStack.arguments?.getInt("eventId")!!
                EventsNavHost(eventId, navController = navController)
            }
        }
        composable(route = NavigationItem.Years.route) {
            CapitalsList()
        }
        composable(route = NavigationItem.Capitals.route) {
            CapitalsList()
        }
    }
}