package com.nmp90.bghistory.compose.eventdetails

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.nmp90.bghistory.compose.errors.ErrorDialog
import com.nmp90.bghistory.compose.progress.CenteredProgressBar
import com.nmp90.bghistory.eventDetails.EventDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventDetailsScreen(viewModel: EventDetailsViewModel = koinViewModel()) {
    val event = viewModel.uiState.collectAsState().value

    when (event) {
        EventDetailsViewModel.UiState.Empty -> CenteredProgressBar()
        is EventDetailsViewModel.UiState.Failure -> ErrorDialog()
        is EventDetailsViewModel.UiState.Success -> {
            TextField(value = event.event.title, onValueChange = {})
        }
    }

}