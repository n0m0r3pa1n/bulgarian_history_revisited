package com.nmp90.bghistory.compose.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        val bottomNavItems = listOf(
            NavigationItem.PeriodsNavGraph.Periods,
            NavigationItem.Years,
            NavigationItem.Capitals,
        )
        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry.value?.destination
        bottomNavItems.forEach { item ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == item.route } == true

            BottomNavigationItem(
                selected = selected,
                onClick = { navController.navigate(item.route) },
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

//    var selectedItem by remember { mutableStateOf(0) }
//    var currentRoute by remember { mutableStateOf(NavigationItem.Periods.route) }
//
//    items.forEachIndexed { index, navigationItem ->
//        if (navigationItem.route == currentRoute) {
//            selectedItem = index
//        }
//    }
//
//    NavigationBar {
//        items.forEachIndexed { index, item ->
//            NavigationBarItem(
//                alwaysShowLabel = true,
//                icon = { Icon(item.icon!!, contentDescription = item.title) },
//                label = { Text(item.title) },
//                selected = selectedItem == index,
//                onClick = {
//                    selectedItem = index
//                    currentRoute = item.route
//                    navController.navigate(item.route) {
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
}