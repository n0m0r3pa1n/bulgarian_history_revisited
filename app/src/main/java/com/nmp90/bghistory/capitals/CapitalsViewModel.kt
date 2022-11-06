package com.nmp90.bghistory.capitals

import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class CapitalsViewModel constructor(private val capitalsRepository: CapitalsRepository) :
    LifecycleViewModel() {

    val uiState = MutableSharedFlow<UiState>(replay = 1)

    init {
        loadCapitals()
    }

    private fun loadCapitals() = viewModelScope.launch {
        runCatching { capitalsRepository.getCapitals() }
            .onSuccess { uiState.emit(UiState.Success(it)) }
            .onFailure { uiState.emit(UiState.Error(it)) }
    }

    sealed interface UiState {
        data class Success(val capitals: List<Capital>) : UiState
        class Error(val throwable: Throwable) : UiState
    }
}
