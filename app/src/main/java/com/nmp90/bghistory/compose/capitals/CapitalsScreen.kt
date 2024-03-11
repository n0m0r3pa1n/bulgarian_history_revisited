package com.nmp90.bghistory.compose.capitals

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.nmp90.bghistory.capitals.Capital
import com.nmp90.bghistory.capitals.CapitalsViewModel
import com.nmp90.bghistory.compose.errors.ErrorDialog
import com.nmp90.bghistory.compose.progress.CenteredProgressBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun CapitalsScreen(
    viewModel: CapitalsViewModel = koinViewModel(),
    onCapitalClick: (capital: Capital) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when (uiState) {
        is CapitalsViewModel.UiState.Error -> ErrorDialog()
        CapitalsViewModel.UiState.Loading -> CenteredProgressBar()
        is CapitalsViewModel.UiState.Success -> CapitalsList(uiState.capitals, onCapitalClick)
    }
}

@Composable
fun CapitalsList(capitals: List<Capital>, onCapitalClick: (capital: Capital) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 200.dp)
    ) {
        items(capitals) { capital ->
            Capital(capital, onCapitalClick)
        }
    }
}

@Composable
fun Capital(capital: Capital, onCapitalClick: (capital: Capital) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable { onCapitalClick(capital) },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            modifier = Modifier
                .height(150.dp)
                .zIndex(1f),
            model = capital.picture,
            contentDescription = null,
        )

        CapitalTitleAndPeriod(capital, modifier = Modifier.zIndex(3f))
    }
}

@Composable
private fun CapitalTitleAndPeriod(capital: Capital, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.8F)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = capital.name,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            modifier = modifier
                .wrapContentWidth()
                .wrapContentHeight()
        )

        Text(
            text = capital.period,
            style = MaterialTheme.typography.titleSmall,
            color = Color.White,
            modifier = modifier
                .wrapContentWidth()
                .wrapContentHeight()
        )
    }
}
