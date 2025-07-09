package com.example.trashtrack.ui.shared.button.icon

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@SuppressLint("SuspiciousModifierThen")
fun Modifier.backgroundBorder(
    color: Color,
    shape: CornerBasedShape
): Modifier = composed {
    val density = LocalDensity.current

    this then drawBehind {
        drawRoundRect(
            color = color,
            cornerRadius = CornerRadius(
                x = shape.topEnd.toPx(size, density)
            ),
            style = Stroke(width = 1.dp.toPx())
        )
    }
}