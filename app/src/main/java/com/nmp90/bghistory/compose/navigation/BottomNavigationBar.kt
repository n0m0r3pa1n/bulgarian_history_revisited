package com.nmp90.bghistory.compose.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination

val bottomNavItems = listOf(
    NavigationItem.PeriodsNavGraph.Periods,
    NavigationItem.Years,
    NavigationItem.CapitalsNavGraph.Capitals,
)

@Composable
fun BottomNavigationBar(currentDestination: NavDestination?, navController: NavController) {
    BottomNavigation {
        bottomNavItems.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

            BottomNavigationItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                selectedContentColor = Color.White,
                label = {
                    item.titleResId?.let {
                        Text(
                            text = stringResource(it),
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                },
                icon = {
                    item.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = "Icon",
                        )
                    }
                }
            )
        }
    }
}