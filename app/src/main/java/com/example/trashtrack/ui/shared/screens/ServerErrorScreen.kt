package com.example.trashtrack.ui.shared.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.shared.button.TTButtonBorder
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun ServerErrorScreenPreview() {
    Surface {
        ServerErrorScreen()
    }
}

@Composable
fun ServerErrorScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.43.dp),
        verticalArrangement = Arrangement.spacedBy(63.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_error),
            contentDescription = null,
            tint = MaterialTheme.colors.green600,
            modifier = Modifier
                .size(140.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Что-то пошло не так",
                color = MaterialTheme.colors.neutral700,
                style = TTTypography.headlineLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = "В работе сервера произошел сбой, попробуй обновить страницу",
                color = MaterialTheme.colors.neutral400,
                style = TTTypography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
        TTButtonBorder(
            text = "Обновите страницу",
            onClick = {},
            style = TTTypography.titleLarge,
            backgroundColor = MaterialTheme.colors.neutral100,
        )
    }
}