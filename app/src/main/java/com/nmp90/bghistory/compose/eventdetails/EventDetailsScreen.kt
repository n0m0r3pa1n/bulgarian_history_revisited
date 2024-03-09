package com.nmp90.bghistory.compose.eventdetails

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.nmp90.bghistory.compose.errors.ErrorDialog
import com.nmp90.bghistory.compose.html.HtmlText
import com.nmp90.bghistory.compose.progress.CenteredProgressBar
import com.nmp90.bghistory.eventDetails.EventDetailsViewModel
import com.nmp90.bghistory.events.Event
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventDetailsScreen(viewModel: EventDetailsViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        EventDetailsViewModel.UiState.Empty -> CenteredProgressBar()
        is EventDetailsViewModel.UiState.Failure -> ErrorDialog()
        is EventDetailsViewModel.UiState.Success -> {
            val event = uiState.event
            EventDetails(event = event)
        }
    }
}

@Preview
@Composable
fun EventDetails(
    @PreviewParameter(EventPreviewParameterProvider::class, limit = 1) event: Event
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = event.title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "${event.year}, ${event.place}",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(8.dp)
        )

        HtmlText(
            html = event.description,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            isInDarkTheme = isSystemInDarkTheme()
        )
    }
}