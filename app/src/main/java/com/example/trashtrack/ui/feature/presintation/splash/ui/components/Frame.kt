package com.example.trashtrack.ui.feature.presintation.splash.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlinx.coroutines.delay

@Composable
fun Frame(
    modifier: Modifier = Modifier,
    width: Float,
    maxHistorySize: Int,
    points: List<Pair<Float,Float>>,
    colorLine: Color
) {
    var currentIndex by remember { mutableStateOf(0) }
    val positionHistory = remember { mutableStateListOf<Offset>() }
    var animationFinished by remember { mutableStateOf(false) }
    var showLine by remember { mutableStateOf(true) }

    val transition = updateTransition(targetState = currentIndex, label = "pointTransition")

    val animatedX by transition.animateFloat(
        transitionSpec = { tween(1000, easing = FastOutSlowInEasing) },
        label = "xValue"
    ) { index -> points[index].first }

    val animatedY by transition.animateFloat(
        transitionSpec = { tween(1000, easing = FastOutSlowInEasing) },
        label = "yValue"
    ) { index -> points[index].second }

    var currentPos by remember { mutableStateOf(Offset(0.5f, 0.5f)) }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = minOf(size.width, size.height) * 0.4f

        currentPos = Offset(
            centerX + (animatedX - 0.5f) * 2 * radius,
            centerY - (animatedY - 0.5f) * 2 * radius
        )

        if (!animationFinished && showLine) {
            positionHistory.add(currentPos)
            if (positionHistory.size > maxHistorySize) {
                positionHistory.removeAt(0)
            }
        }

        if (positionHistory.size > 1 && showLine) {
            val path = Path().apply {
                moveTo(positionHistory.first().x, positionHistory.first().y)
                positionHistory.forEach { point ->
                    lineTo(point.x, point.y)
                }
            }

            drawPath(
                path = path,
                color = colorLine,
                style = Stroke(
                    width = width,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }

        if (!animationFinished) {
            drawCircle(
                color = Color.White.copy(0f),
                radius = 25f,
                center = currentPos
            )
        }

    }

    LaunchedEffect(currentPos) {

        if (!animationFinished && showLine) {
            positionHistory.add(currentPos)
        }

        if (positionHistory.isEmpty() || (currentPos - positionHistory.last()).getDistanceSquared() > maxHistorySize) {
            positionHistory.add(currentPos)
        }
    }

    LaunchedEffect(currentIndex) {
        if (currentIndex < points.size - 1) {
            delay(1100)
            currentIndex++
        } else {
            animationFinished = true
        }
    }
}