package com.nmp90.bghistory.compose.events

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.nmp90.bghistory.topics.TopicsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventsNavHost(eventId: Int, navController: NavController) {
    Text(text = "Event id $eventId", modifier = Modifier.clickable { navController.popBackStack() })
}