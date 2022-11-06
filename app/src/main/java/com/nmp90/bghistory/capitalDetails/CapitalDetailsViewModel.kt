package com.nmp90.bghistory.capitalDetails

import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.R
import com.nmp90.bghistory.capitals.Capital
import com.nmp90.bghistory.capitals.CapitalsRepository
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CapitalDetailsViewModel constructor(private val capitalsRepository: CapitalsRepository) : LifecycleViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState.Empty)

    fun getCapital(capitalId: String) = viewModelScope.launch {
        runCatching { capitalsRepository.getCapital(capitalId) }
            .onSuccess { uiState.emit(UiState.Success(it)) }
            .onFailure { uiState.emit(UiState.Failure(it)) }
    }

    sealed class UiState(val displayedChildId: Int) {
        data class Success(val capital: Capital): UiState(R.id.card_view)
        data class Failure(val throwable: Throwable) : UiState(R.id.pb_loading)
        object Empty : UiState(R.id.pb_loading)
    }
}
