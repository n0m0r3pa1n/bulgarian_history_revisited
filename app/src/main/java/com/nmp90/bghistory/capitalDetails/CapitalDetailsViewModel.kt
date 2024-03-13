package com.nmp90.bghistory.capitalDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.capitals.Capital
import com.nmp90.bghistory.capitals.CapitalsRepository
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CapitalDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val capitalsRepository: CapitalsRepository
) : LifecycleViewModel() {
    init {
        getCapital(savedStateHandle.get<String>("capitalId")!!)
    }

    val uiState = MutableStateFlow<UiState>(UiState.Empty)

    private fun getCapital(capitalId: String) = viewModelScope.launch {
        runCatching { capitalsRepository.getCapital(capitalId) }
            .onSuccess { uiState.emit(UiState.Success(it)) }
            .onFailure { uiState.emit(UiState.Failure(it)) }
    }

    sealed interface UiState {
        data class Success(val capital: Capital) : UiState
        data class Failure(val throwable: Throwable) : UiState
        object Empty : UiState
    }
}
