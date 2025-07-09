package com.example.trashtrack.ui.feature.user.map.ui.bottomsheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun ErrorAddressPreview() {
    TTModalBottomSheet(
        onDismissRequest = {}
    ) {
        ErrorAddress(
            onBack = {}
        )
    }
}

@Composable
fun ErrorAddress(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 22.dp,
                bottom = 20.dp,
                end = 22.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ошибка",
            color = MaterialTheme.colors.neutral950,
            style = TTTypography.headlineLarge
        )
        Spacer(modifier = Modifier.height(21.dp))
        Text(
            text = "Адрес не входит  в зону действия сервиса",
            color = MaterialTheme.colors.neutral500,
            style = TTTypography.titleLarge
        )
        Spacer(modifier = Modifier.height(49.dp))
        TTButton(
            text = "ОК",
            onClick = onBack,
        )
    }
}