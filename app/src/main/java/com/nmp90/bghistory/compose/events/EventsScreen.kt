package com.nmp90.bghistory.compose.events

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nmp90.bghistory.compose.errors.ErrorDialog
import com.nmp90.bghistory.compose.progress.CenteredProgressBar
import com.nmp90.bghistory.events.Event
import com.nmp90.bghistory.events.EventsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventsScreen(
    onEventClick: (event: Event) -> Unit,
    viewModel: EventsViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        EventsViewModel.UiState.Empty -> CenteredProgressBar()
        is EventsViewModel.UiState.Failure -> ErrorDialog()
        is EventsViewModel.UiState.Success -> {
            EventsList(uiState.events, onEventClick)
        }
    }
}

@Composable
fun EventsList(events: List<Event>, onEventClick: (event: Event) -> Unit) {
    LazyColumn {
        items(events) { event ->
            Event(event, onEventClick)
        }
    }
}

@Composable
fun Event(event: Event, onEventClick: (event: Event) -> Unit) {
    Text(
        text = event.title,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .clickable { onEventClick(event) }
            .padding(8.dp)
            .fillMaxWidth()
    )
}