package com.nmp90.bghistory.eventDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.R
import com.nmp90.bghistory.events.Event
import com.nmp90.bghistory.events.EventsRepository
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventDetailsViewModel(
    private val eventsRepository: EventsRepository,
    savedStateHandle: SavedStateHandle,
    ) : LifecycleViewModel() {

    private val eventId = savedStateHandle.get<String>("eventId")!!
    val uiState = MutableStateFlow<UiState>(UiState.Empty)

    init {
        getEvent()
    }

    private fun getEvent() = viewModelScope.launch {
        runCatching { eventsRepository.getEvent(eventId) }
            .onFailure { uiState.emit(UiState.Failure(it)) }
            .onSuccess { uiState.emit(UiState.Success(event = it)) }
    }

    sealed interface UiState {
        data class Success(val event: Event): UiState
        data class Failure(val throwable: Throwable) : UiState
        object Empty : UiState
    }
}
