package com.nmp90.bghistory.years

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class YearsViewModel constructor(private val yearsRepository: YearsRepository) :
    LifecycleViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    val searchResults: StateFlow<List<Year>> =
        snapshotFlow { searchQuery }
            .map {
                yearsRepository.searchYears(searchQuery)
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.WhileSubscribed(5_000)
            )

    val uiState = MutableStateFlow<UiState>(UiState.EmptyResult)

    fun searchYears(query: String) = viewModelScope.launch {
        runCatching { yearsRepository.searchYears(query) }
            .onSuccess { uiState.emit(UiState.SearchResult(query, it)) }
            .onFailure { uiState.emit(UiState.Error(it)) }
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    sealed interface UiState {
        data class SearchResult(val searchTerm: String, val years: List<Year>) : UiState
        data class Error(val throwable: Throwable) : UiState
        object EmptyResult : UiState
    }
}
