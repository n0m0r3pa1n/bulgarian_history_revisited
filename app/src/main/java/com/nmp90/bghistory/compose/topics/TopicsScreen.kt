package com.nmp90.bghistory.compose.topics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.nmp90.bghistory.compose.errors.ErrorDialog
import com.nmp90.bghistory.compose.progress.CenteredProgressBar
import com.nmp90.bghistory.topics.Topic
import com.nmp90.bghistory.topics.TopicsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopicsScreen(viewModel: TopicsViewModel = koinViewModel(), onTopicClick: (topic: Topic) -> Unit) {
    val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        TopicsViewModel.UiState.Empty -> CenteredProgressBar()
        is TopicsViewModel.UiState.Failure -> ErrorDialog()
        is TopicsViewModel.UiState.Success -> {
            TopicsList(topics = uiState.topics, onTopicClick)
        }
    }
}

@Composable
fun TopicsList(topics: List<Topic>, onTopicClick: (topic: Topic) -> Unit) {
    LazyColumn {
        items(topics) { topic ->
            Topic(topic, onTopicClick)
        }
    }
}

@Composable
fun Topic(topic: Topic, onTopicClick: (topic: Topic) -> Unit) {
    Text(
        text = topic.name,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.clickable { onTopicClick(topic) }
    )
}