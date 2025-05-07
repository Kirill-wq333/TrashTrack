package com.example.trashtrack.ui.feature.user.main.ui.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.trashtrack.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.shared.button.TTButtonBorder
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun UpdateProjectPreview() {
    Surface {
        TTModalBottomSheet(
            onDismissRequest = {}
        ) {
            UpdateProject()
        }
    }
}

@Composable
fun UpdateProject() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.update),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(114.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 23.dp, bottom = 44.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Доступно обновление!",
                color = MaterialTheme.colors.neutral950,
                style = TTTypography.displaySmall
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Быстрей меня обнови, я стал ещё лучше!",
                color = MaterialTheme.colors.primary600,
                style = TTTypography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Actions(
                onUpdate = {},
                onCancel = {}
            )
        }
    }
}

@Composable
fun Actions(
    onCancel: () -> Unit,
    onUpdate: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TTButtonBorder(
            modifier = Modifier.weight(0.5f),
            onClick = onCancel,
            text = "Не сейчас",
            style = TTTypography.titleMedium
        )
        TTButton(
            modifier = Modifier.weight(0.5f),
            onClick = onUpdate,
            text = "Обновить",
            style = TTTypography.titleMedium
        )
    }
}