package com.nmp90.bghistory.compose.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nmp90.bghistory.compose.capitals.CapitalDetailsScreen
import com.nmp90.bghistory.compose.capitals.CapitalsScreen
import com.nmp90.bghistory.compose.eventdetails.EventDetailsScreen
import com.nmp90.bghistory.compose.events.EventsScreen
import com.nmp90.bghistory.compose.navigation.NavigationItem.CapitalsNavGraph
import com.nmp90.bghistory.compose.navigation.NavigationItem.PeriodsNavGraph
import com.nmp90.bghistory.compose.topics.TopicsScreen
import com.nmp90.bghistory.compose.years.YearsScreen

@Composable
fun Navigations(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = PeriodsNavGraph.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        navigation(
            startDestination = PeriodsNavGraph.requireStartDestination(),
            route = PeriodsNavGraph.route,
        ) {
            composable(
                route = PeriodsNavGraph.Periods.route,
                enterTransition = { fadeIn(initialAlpha = 0.4f) },
                popEnterTransition = { fadeIn(initialAlpha = 0.4f) },
                popExitTransition = { fadeOut() },
                exitTransition = { fadeOut() }
            ) {
                TopicsScreen(onTopicClick = { topic ->
                    navController.navigate(PeriodsNavGraph.Events.navigate(topic.id))
                })
            }
            composable(
                route = PeriodsNavGraph.Events.route,
                arguments = PeriodsNavGraph.Events.arguments,
                enterTransition = { fadeIn(initialAlpha = 0.4f) },
                popEnterTransition = { fadeIn(initialAlpha = 0.4f) },
                popExitTransition = { fadeOut() },
                exitTransition = { fadeOut() }
            ) { backStack ->
                EventsScreen(onEventClick = { event ->
                    navController.navigate(PeriodsNavGraph.Event.navigate(event.id))
                })
            }

            composable(
                route = PeriodsNavGraph.Event.route,
                arguments = PeriodsNavGraph.Event.arguments,
                enterTransition = { fadeIn(initialAlpha = 0.4f) },
                popEnterTransition = { fadeIn(initialAlpha = 0.4f) },
                popExitTransition = { fadeOut() },
                exitTransition = { fadeOut() }
            ) { backStack -> EventDetailsScreen() }
        }
        composable(route = NavigationItem.Years.route) {
            YearsScreen()
        }

        navigation(
            startDestination = CapitalsNavGraph.requireStartDestination(),
            route = CapitalsNavGraph.route,
        ) {
            composable(route = CapitalsNavGraph.Capitals.route) {
                CapitalsScreen(onCapitalClick = {
                    navController.navigate(CapitalsNavGraph.CapitalDetails.navigate(it.id))
                })
            }

            composable(
                route = CapitalsNavGraph.CapitalDetails.route,
                arguments = CapitalsNavGraph.CapitalDetails.arguments,
            ) { backStack -> CapitalDetailsScreen() }
        }
    }
}