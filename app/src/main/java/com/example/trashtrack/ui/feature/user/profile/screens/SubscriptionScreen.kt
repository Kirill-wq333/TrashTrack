package com.example.trashtrack.ui.feature.user.profile.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun SubscriptionScreen(
    backButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                start = 17.dp,
                top = 16.dp
            )
    ) {
        BackButton(
            backButton = backButton,
            paddingStart = 0.dp,
            color = MaterialTheme.colors.neutral300
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = "Мои подписки",
            color = MaterialTheme.colors.black,
            style = TTTypography.displaySmall,
            modifier = Modifier
                .padding(start = 9.dp)
        )
    }
}