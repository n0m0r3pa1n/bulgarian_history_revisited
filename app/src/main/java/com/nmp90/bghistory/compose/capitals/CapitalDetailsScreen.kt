package com.nmp90.bghistory.compose.capitals

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nmp90.bghistory.capitalDetails.CapitalDetailsViewModel
import com.nmp90.bghistory.capitals.Capital
import com.nmp90.bghistory.compose.errors.ErrorDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun CapitalDetailsScreen(viewModel: CapitalDetailsViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when (uiState) {
        CapitalDetailsViewModel.UiState.Empty -> CircularProgressIndicator()
        is CapitalDetailsViewModel.UiState.Failure -> ErrorDialog()
        is CapitalDetailsViewModel.UiState.Success -> CapitalDetails(uiState.capital)
    }
}

@Composable
private fun CapitalDetails(capital: Capital) {
    Text(text = capital.name)
}