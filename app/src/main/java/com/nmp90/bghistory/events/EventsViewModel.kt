package com.nmp90.bghistory.events

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.R
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventsViewModel(
    savedStateHandle: SavedStateHandle,
    private val eventsRepository: EventsRepository
) : LifecycleViewModel() {

    init {
        val topicId = savedStateHandle.get<Int>("topicId")!!
        getEvents(topicId)
    }
    val uiState = MutableStateFlow<UiState>(UiState.Empty)

    fun getEvents(topicId: Int) = viewModelScope.launch {
        runCatching { eventsRepository.getEvents(topicId) }
            .onSuccess { uiState.emit(UiState.Success(it)) }
            .onFailure { uiState.emit(UiState.Failure(it)) }
    }

    sealed class UiState(val displayedChildId: Int) {
        data class Success(val events: List<Event>) : UiState(R.id.rv_events)
        data class Failure(val throwable: Throwable) : UiState(R.id.pb_loading)
        object Empty : UiState(R.id.pb_loading)
    }
}
