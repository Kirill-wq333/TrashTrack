package com.example.trashtrack.ui.feature.user.introduction.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun HeaderIntroduction(
    visibleBackButton: Boolean,
    visibleSkipButton: Boolean,
    backButton: () -> Unit,
    skipButton: () -> Unit

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 40.5.dp,
                start = 42.6.dp,
                end = 30.15.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        if (visibleBackButton) {
            BackButton(
                backButton = backButton,
                paddingStart = 0.dp
            )
        }

        if (visibleSkipButton) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    style = TTTypography.titleLarge,
                    color = MaterialTheme.colors.neutral800,
                    modifier = Modifier
                        .clickable(
                            onClick = skipButton
                        )
                        .padding(top = 7.5.dp, bottom = 12.5.dp)
                )
            }
        }
    }
}