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
            route = "events?eventId={eventId}",
            icon = Icons.Rounded.List,
            titleResId = R.string.events_and_years,
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) {
            fun navigate(eventId: Int): String {
                return "events?eventId=$eventId"
            }
        }
    }

    object Years : NavigationItem("years", Icons.Rounded.List, R.string.events_and_years)


    object Capitals : NavigationItem("capitals", Icons.Rounded.Info, R.string.capitals)
}