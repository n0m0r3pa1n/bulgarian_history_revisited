package com.nmp90.bghistory.compose.eventdetails

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.nmp90.bghistory.eventDetails.EventDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventDetailsScreen(viewModel: EventDetailsViewModel = koinViewModel()) {
    val event = viewModel.uiState.collectAsState().value

    when (event) {
        EventDetailsViewModel.UiState.Empty -> ""
        is EventDetailsViewModel.UiState.Failure -> ""
        is EventDetailsViewModel.UiState.Success -> {
            TextField(value = event.event.title, onValueChange = {})
        }
    }

}