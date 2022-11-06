package com.nmp90.bghistory.topics

import androidx.databinding.ObservableInt
import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.R
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TopicsViewModel constructor(private val topicsRepository: TopicsRepository) : LifecycleViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState.Empty)
    init {
        loadTopics()
    }

    private fun loadTopics() = viewModelScope.launch {
        runCatching { topicsRepository.getTopics() }
            .onSuccess {
                uiState.emit(UiState.Success(topics = it))
            }
            .onFailure {
                uiState.emit(UiState.Failure(throwable = it))
            }

    }

    sealed class UiState(val displayedChildId: Int) {
        data class Success(val topics: List<Topic>): UiState(R.id.rv_topics)
        data class Failure(val throwable: Throwable) : UiState(R.id.pb_loading)
        object Empty : UiState(R.id.pb_loading)
    }
}
