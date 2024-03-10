package com.nmp90.bghistory.compose.years

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nmp90.bghistory.compose.progress.CenteredProgressBar
import com.nmp90.bghistory.years.Year
import com.nmp90.bghistory.years.YearsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun YearsScreen(viewModel: YearsViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        YearsViewModel.UiState.EmptyResult -> {
            Text(text = "Empty")
        }

        is YearsViewModel.UiState.Error -> CenteredProgressBar()
        is YearsViewModel.UiState.SearchResult -> YearsList(uiState.years)
        is YearsViewModel.UiState.YearsResult -> YearsList(uiState.years)
    }
}

@Composable
private fun YearsList(years: List<Year>) {
    Column {
        LazyColumn {
            items(years) { year ->
                Row(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = year.year.toString(),
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                    Text(
                        text = year.name,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }
    }
}