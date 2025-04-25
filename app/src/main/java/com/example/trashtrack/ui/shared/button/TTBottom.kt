package com.example.trashtrack.ui.shared.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun TTBottom(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    color: Color = MaterialTheme.colors.green600,
    enable: Boolean = true,
    style: TextStyle = TTTypography.headlineLarge
) {
    Box(
        modifier = modifier
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
            style = style,
            modifier = Modifier
                .padding(vertical = 13.dp)
        )
    }
}

@Composable
fun TTBottomBorder(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    colorBorder: Color = Color(0xFFEDECEC),
    backgroundColor: Color = Color.Unspecified,
    enable: Boolean = false,
    style: TextStyle = TTTypography.headlineLarge
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                enabled = enable
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = colorBorder,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.primary,
            style = style,
            modifier = Modifier
                .padding(vertical = 13.dp)
        )
    }
}