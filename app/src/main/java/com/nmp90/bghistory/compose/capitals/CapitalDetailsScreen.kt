package com.nmp90.bghistory.compose.capitals

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.nmp90.bghistory.capitalDetails.CapitalDetailsViewModel
import com.nmp90.bghistory.capitals.Capital
import com.nmp90.bghistory.compose.errors.ErrorDialog
import com.nmp90.bghistory.compose.html.HtmlText
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Row(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(150.dp)
                    .clip(RoundedCornerShape(size = 4.dp))
                    .zIndex(1f)
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?q=${capital.lat},${capital.lng}")
                        )

                        context.startActivity(intent)
                    },
                model = capital.picture,
                contentDescription = null,
            )
        }
        Text(
            text = capital.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        )
        Text(
            text = capital.period,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        )

        HtmlText(
            html = capital.content,
            isInDarkTheme = isSystemInDarkTheme(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                .wrapContentHeight()
        )
    }
}