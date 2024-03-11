package com.nmp90.bghistory.compose.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.nmp90.bghistory.R

sealed class NavigationItem(
    val route: String,
    val icon: ImageVector?,
    @StringRes val titleResId: Int? = null,
    val startDestination: String? = null,
    val arguments: List<NamedNavArgument> = emptyList(),
) {

    fun requireStartDestination() = startDestination!!

    object PeriodsNavGraph : NavigationItem(
        route = "periodsNavGraph",
        startDestination = "periods",
        icon = null,
        titleResId = null
    ) {
        object Periods : NavigationItem("periods", Icons.Rounded.List, R.string.periods)

        object Events : NavigationItem(
            route = "events?topicId={topicId}",
            icon = Icons.Rounded.List,
            titleResId = R.string.events_and_years,
            arguments = listOf(navArgument("topicId") { type = NavType.IntType })
        ) {
            fun navigate(topicId: Int): String {
                return "events?topicId=$topicId"
            }
        }

        object Event : NavigationItem(
            route = "events/{eventId}",
            icon = Icons.Rounded.List,
            titleResId = null,
            arguments = listOf(navArgument("eventId") { type = NavType.StringType })
        ) {
            fun navigate(eventId: String): String {
                return "events/$eventId"
            }
        }
    }

    object Years : NavigationItem("years", Icons.Rounded.List, R.string.events_and_years)


    object CapitalsNavGraph : NavigationItem(
        route = "capitalsNavGraph",
        startDestination = "capitals",
        icon = null,
        titleResId = null
    ) {
        object Capitals : NavigationItem("capitals", Icons.Rounded.Info, R.string.capitals)

        object CapitalDetails : NavigationItem(
            route = "capitals/{capitalId}",
            icon = Icons.Rounded.List,
            titleResId = R.string.capitals,
            arguments = listOf(navArgument("capitalId") { type = NavType.StringType })
        ) {
            fun navigate(capitalId: String): String {
                return "capitals/${capitalId}"
            }
        }
    }
}