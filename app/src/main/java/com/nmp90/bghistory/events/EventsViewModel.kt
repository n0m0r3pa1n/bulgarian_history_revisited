package com.nmp90.bghistory.events

import androidx.databinding.ObservableInt
import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.R
import com.nmp90.bghistory.eventDetails.EventDetailsViewModel
import com.nmp90.bghistory.extensions.toReactiveSource
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventsViewModel constructor(private val eventsRepository: EventsRepository) : LifecycleViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState.Empty)

    fun getEvents(topicId: Int) = viewModelScope.launch {
        runCatching { eventsRepository.getEvents(topicId) }
            .onSuccess { uiState.emit(UiState.Success(it)) }
            .onFailure { uiState.emit(UiState.Failure(it)) }
    }

    sealed class UiState(val displayedChildId: Int) {
        data class Success(val events: List<Event>): UiState(R.id.rv_events)
        data class Failure(val throwable: Throwable) : UiState(R.id.pb_loading)
        object Empty : UiState(R.id.pb_loading)
    }
}
