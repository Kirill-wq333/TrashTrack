package com.example.trashtrack.ui.shared.button.back

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    paddingTop: Dp = 0.dp,
    paddingBottom: Dp = 0.dp,
    paddingEnd: Dp = 0.dp,
    paddingStart: Dp = 33.dp,
    paddingIcon: Dp = 9.dp,
    sizeIcon: Dp = 24.dp,
    backButton: () -> Unit,
    color: Color = Color(0xFFF6F6F5)
) {
    Box(
        modifier = Modifier
            .padding(
                start = paddingStart,
                top = paddingTop,
                end = paddingEnd,
                bottom = paddingBottom
            )
            .clickable(
                onClick = backButton
            )
            .background(
                color = color,
                shape = RoundedCornerShape(6.dp)
            ),

        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_chevron_left),
            contentDescription = null,
            tint = Color.Black,
            modifier = modifier
                .size(sizeIcon)
                .padding(paddingIcon)
        )
    }
}