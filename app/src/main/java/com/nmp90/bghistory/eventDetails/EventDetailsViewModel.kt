package com.nmp90.bghistory.eventDetails

import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.R
import com.nmp90.bghistory.events.Event
import com.nmp90.bghistory.events.EventsRepository
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventDetailsViewModel constructor(private val eventsRepository: EventsRepository) : LifecycleViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState.Empty)

    fun getEvent(eventId: String) = viewModelScope.launch {
        runCatching { eventsRepository.getEvent(eventId) }
            .onFailure { uiState.emit(UiState.Failure(it)) }
            .onSuccess { uiState.emit(UiState.Success(event = it)) }
    }

    sealed class UiState(val displayedChildId: Int) {
        data class Success(val event: Event): UiState(R.id.content)
        data class Failure(val throwable: Throwable) : UiState(R.id.loader)
        object Empty : UiState(R.id.loader)
    }
}
