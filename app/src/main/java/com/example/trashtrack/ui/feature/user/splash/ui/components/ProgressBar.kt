package com.example.trashtrack.ui.feature.user.splash.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.theme.TTTypography
import kotlinx.coroutines.delay

@Composable
fun TimerProgressBar(
    totalTime: Long = 5000L,
    inactiveBarColor: Color = Color.LightGray,
    activeBarColor: Color = Color.Green,
    progressTextColor: Color = Color.Black,
    currentStep: Boolean = true,
    maximumValue: Boolean,
    percentage: Float,
    barHeight: Dp = 16.dp,
    cornerRadius: Dp = 8.dp
) {
    var currentPercentage by remember { mutableFloatStateOf(percentage) }
    var isVisible by remember { mutableStateOf(currentStep) }
    var isCompleted by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = currentStep) {
        if (!currentStep) return@LaunchedEffect

        if (maximumValue) {
            while (currentPercentage < 0.8f) {
                delay(20L)
                currentPercentage = (currentPercentage + (20f / totalTime)).coerceAtMost(0.8f)
            }
            delay(500L)
            isVisible = false
        } else{
            while (currentPercentage < 1f) {
                delay(20L)
                currentPercentage = (currentPercentage + (20f / (totalTime * 0.2f))).coerceAtMost(1f)
            }
            isVisible = true
        }

        isCompleted = true



    }

    AnimatedVisibility(
        visible = isVisible && currentStep,
        enter = fadeIn(),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text =  stringResource(R.string.progress_bar_title,(currentPercentage * 100).toInt()),
                color = progressTextColor,
                style = TTTypography.headlineLarge,
                modifier = Modifier.align(Alignment.Start)
            )
            ProgressBar(
                percentage = currentPercentage,
                inactiveBarColor = inactiveBarColor,
                activeBarColor = activeBarColor,
                barHeight = barHeight,
                cornerRadius = cornerRadius
            )
        }
    }
}

@Composable
fun ProgressBar(
    percentage: Float,
    inactiveBarColor: Color,
    activeBarColor: Color,
    barHeight: Dp,
    cornerRadius: Dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(barHeight)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val cornerRadiusPx = cornerRadius.toPx()

        // Draw inactive bar
        drawRoundRect(
            color = inactiveBarColor,
            topLeft = Offset(0f, 0f),
            size = Size(canvasWidth, canvasHeight),
            cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx)
        )

        drawRoundRect(
            color = activeBarColor,
            topLeft = Offset(0f, 0f),
            size = Size(canvasWidth * percentage, canvasHeight),
            cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTimerProgressBar() {
    TimerProgressBar(
        currentStep = true,
        percentage = 0.8f,
        maximumValue = true
    )
}