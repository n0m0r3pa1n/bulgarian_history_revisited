package com.nmp90.bghistory.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nmp90.bghistory.compose.navigation.BottomNavigationBar
import com.nmp90.bghistory.compose.navigation.BulgarianHistorySearchAppBar
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
