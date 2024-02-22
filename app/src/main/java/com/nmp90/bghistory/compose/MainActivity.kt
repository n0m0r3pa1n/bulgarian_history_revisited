package com.nmp90.bghistory.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nmp90.bghistory.compose.navigation.BottomNavigationBar
import com.nmp90.bghistory.compose.navigation.BulgarianHistorySearchAppBar
import com.nmp90.bghistory.compose.navigation.NavigationItem
import com.nmp90.bghistory.compose.navigation.Navigations
import com.nmp90.bghistory.compose.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scrollBehavior =
                TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar = { BottomNavigationBar(navController) },
                        topBar = { BulgarianHistorySearchAppBar(scrollBehavior, navController) },
                        content = { innerPadding ->
                            Navigations(navController = navController, innerPadding)
                        }
                    )
                }
            }
        }
    }
}

//                    val navBackStackEntry by navController.currentBackStackEntryAsState()
//                    val scrollBehavior =
//                        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
//                    Scaffold(
//                        topBar = { BulgarianHistorySearchAppBar(scrollBehavior, navController) },
//                        bottomBar = {
//                            BottomNavigation {
//                                val items = listOf(
//                                    NavigationItem.PeriodsMain,
//                                    NavigationItem.Years,
//                                    NavigationItem.Capitals,
//                                )
//                                val currentDestination = navBackStackEntry?.destination
//                                items.forEach { screen ->
//                                    BottomNavigationItem(
//                                        icon = {
//                                            Icon(
//                                                Icons.Filled.Favorite,
//                                                contentDescription = null
//                                            )
//                                        },
//                                        label = { Text(stringResource(screen.titleResId)) },
//                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
//                                        onClick = {
//                                            navController.navigate(screen.route) {
//                                                // Pop up to the start destination of the graph to
//                                                // avoid building up a large stack of destinations
//                                                // on the back stack as users select items
//                                                popUpTo(navController.graph.findStartDestination().id) {
//                                                    saveState = true
//                                                }
//                                                // Avoid multiple copies of the same destination when
//                                                // reselecting the same item
//                                                launchSingleTop = true
//                                                // Restore state when reselecting a previously selected item
//                                                restoreState = true
//                                            }
//                                        }
//                                    )
//                                }
//                            }
//                        }
//                    ) { innerPadding ->
//                        Box(
//                            modifier = Modifier.padding(innerPadding)
//                        ) {
//                            Navigations(navController = navController)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
