package com.nmp90.bghistory.compose.topics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nmp90.bghistory.compose.errors.ErrorDialog
import com.nmp90.bghistory.compose.navigation.NavigationItem
import com.nmp90.bghistory.topics.Topic
import com.nmp90.bghistory.topics.TopicsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopicsListNavHost(viewModel: TopicsViewModel = koinViewModel(), navController: NavController) {
    val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        TopicsViewModel.UiState.Empty -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }

        is TopicsViewModel.UiState.Failure -> ErrorDialog()
        is TopicsViewModel.UiState.Success -> {
            TopicsList(topics = uiState.topics, viewModel::onTopicClick)
        }
    }

    val navState = viewModel.navigationState.collectAsState().value
    when (navState) {
        is TopicsViewModel.NavigationState.NavigateToTopic -> {
            viewModel.navigationFinished()
            navController.navigate(NavigationItem.PeriodsNavGraph.Events.navigate(navState.topicId))
        }

        null -> Unit
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