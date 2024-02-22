package com.nmp90.bghistory.topics

import androidx.lifecycle.viewModelScope
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TopicsViewModel(private val topicsRepository: TopicsRepository) : LifecycleViewModel() {

    val uiState = MutableStateFlow<UiState>(UiState.Empty)
    val navigationState = MutableStateFlow<NavigationState?>(null)

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

    fun onTopicClick(topic: Topic) = viewModelScope.launch {
        navigationState.emit(NavigationState.NavigateToTopic(topic.id))
    }

    fun navigationFinished() = viewModelScope.launch {
        navigationState.emit(null)
    }

    sealed interface UiState {
        data class Success(val topics: List<Topic>) : UiState
        data class Failure(val throwable: Throwable) : UiState
        object Empty : UiState
    }

    sealed interface NavigationState {
        data class NavigateToTopic(val topicId: Int) : NavigationState
    }
}
