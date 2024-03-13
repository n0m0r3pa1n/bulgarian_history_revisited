package com.nmp90.bghistory.compose.years

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nmp90.bghistory.R
import com.nmp90.bghistory.compose.progress.CenteredProgressBar
import com.nmp90.bghistory.years.Year
import com.nmp90.bghistory.years.YearsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun YearsScreen(viewModel: YearsViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState().value
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    SearchScreen(
        searchQuery = viewModel.searchQuery,
        searchResults = searchResults,
        onSearchQueryChange = { viewModel.onSearchQueryChange(it) }
    )
    when (uiState) {
        YearsViewModel.UiState.EmptyResult -> {
            Text(text = stringResource(id = R.string.no_data))
        }

        is YearsViewModel.UiState.Error -> CenteredProgressBar()
        is YearsViewModel.UiState.SearchResult -> YearsList(uiState.years)
    }
}

@Composable
private fun YearsList(years: List<Year>) {
    Column {
        LazyColumn {
            items(years) { year -> YearItem(year) }
        }
    }
}

@Composable
private fun YearItem(year: Year) {
    Row(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = year.year.toString(),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
        Text(
            color = MaterialTheme.colorScheme.onSurface,
            text = year.name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchQuery: String,
    searchResults: List<Year>,
    onSearchQueryChange: (String) -> Unit
) {
    SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        onSearch = {},
        placeholder = {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(id = R.string.years_search_hint)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        content = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = searchResults.size,
                    itemContent = { index ->
                        val year = searchResults[index]
                        YearItem(year = year)
                    }
                )
            }
        },
        trailingIcon = {},
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp
    )
}