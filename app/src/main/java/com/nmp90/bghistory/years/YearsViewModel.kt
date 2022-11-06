package com.nmp90.bghistory.years

import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.extensions.toReactiveSource
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class YearsViewModel constructor(private val yearsRepository: YearsRepository) :
    LifecycleViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState.EmptyResult)

    init {
        loadYears()
    }

    private fun loadYears() = viewModelScope.launch {
        runCatching { yearsRepository.getYears() }
            .onSuccess { uiState.emit(UiState.YearsResult(it)) }
            .onFailure { uiState.emit(UiState.Error(it)) }
    }

    fun searchYears(query: String) = viewModelScope.launch {
        runCatching { yearsRepository.searchYears(query) }
            .onSuccess { uiState.emit(UiState.SearchResult(it)) }
            .onFailure { uiState.emit(UiState.Error(it)) }
    }

    sealed interface UiState {
        data class YearsResult(val years: List<Year>) : UiState
        data class SearchResult(val years: List<Year>) : UiState
        data class Error(val throwable: Throwable) : UiState
        object EmptyResult : UiState
    }
}
