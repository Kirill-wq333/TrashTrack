package com.example.trashtrack.ui.shared.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.theme.TTTypography

@Composable
fun TTBottom(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    color: Color,
    enable: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                enabled = enable
            )
            .background(
                color = color,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            style = TTTypography.headlineLarge,
            modifier = Modifier
                .padding(vertical = 13.dp)
        )
    }
}