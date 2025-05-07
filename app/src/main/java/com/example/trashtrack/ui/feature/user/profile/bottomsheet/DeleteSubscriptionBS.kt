package com.example.trashtrack.ui.feature.user.profile.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun DeleteSubscriptionBSPreview() {
    TTModalBottomSheet(
        onDismissRequest = {}
    ) {
        DeleteSubscriptionBS(
            backClick = {}
        )
    }
}

@Composable
fun DeleteSubscriptionBS(
    backClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 22.dp, end = 22.dp, bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Удаление подписки",
            color = MaterialTheme.colors.neutral950,
            style = TTTypography.headlineLarge
        )
        Spacer(modifier = Modifier.height(21.dp))
        Text(
            text = "Вы уверены, что хотите удалить подписку?",
            color = MaterialTheme.colors.neutral500,
            style = TTTypography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(49.dp))
        TTButton(
            text = "Да",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(11.dp))
        TTButton(
            text = "Отменить",
            onClick = backClick,
            color = MaterialTheme.colors.neutral400
        )
    }
}