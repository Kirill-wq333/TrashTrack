package com.example.trashtrack.ui.feature.user.splash.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trashtrack.ui.feature.user.splash.ui.components.Frame
import com.example.trashtrack.ui.feature.user.splash.ui.components.TimerProgressBar
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Preview
@Composable
private fun DualAxisAnimationPreview() {
        DualAxisAnimationScreen(navController = rememberNavController())
}

@Composable
fun DualAxisAnimationScreen(
    navController: NavHostController
) {

    var listStep by remember { mutableStateOf(false) }
    var timerStep by remember { mutableStateOf(true) }
    var frameStep by remember { mutableStateOf(false) }
    var trashTrackStep by remember { mutableStateOf(false) }
    var circleStep by remember { mutableStateOf(false) }
    var dakantStep by remember { mutableStateOf(false) }

    var allAnimationsFinished by remember { mutableStateOf(false) }

    val boxOffsetY = remember { Animatable(0f) }
    val boxSize = remember { Animatable(0f) }
    val padding = remember { Animatable(27f) }

    val points = listOf(
        Pair(0.5f, 0.3f),
        Pair(0.49f, 0.32f),
        Pair(0.39f, 0.36f),
        Pair(0.35f, 0.44f),
        Pair(0.48f, 0.60f),
        Pair(0.50f, 0.62f),
        Pair(0.52f, 0.60f),
        Pair(0.65f, 0.44f),
        Pair(0.65f, 0.40f),
        Pair(0.56f, 0.34f)
    )
    val point = listOf(
        Pair(0.5f, 0.365f),
        Pair(0.5f, 0.39f),
        Pair(0.43f, 0.42f),
        Pair(0.5f, 0.39f),
        Pair(0.57f, 0.42f),
        Pair(0.5f, 0.39f),
        Pair(0.5f, 0.435f),
        Pair(0.45f, 0.455f),
        Pair(0.5f, 0.435f),
        Pair(0.55f, 0.455f),
        Pair(0.5f, 0.435f),
        Pair(0.5f, 0.48f),
        Pair(0.47f, 0.49f),
        Pair(0.5f, 0.48f),
        Pair(0.53f, 0.49f),
        Pair(0.5f, 0.48f),
        Pair(0.5f, 0.54f),
    )


    LaunchedEffect(allAnimationsFinished) {
        if (allAnimationsFinished) {
            navController.navigate("aLittleMore") {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    LaunchedEffect(timerStep) {
        launch {
            delay(1000)
            timerStep = true
            listStep = true
        }

            delay(18000)
            frameStep = true

            delay(10000)
            circleStep = true
            boxOffsetY.animateTo(
                targetValue = 62f,
                animationSpec = tween(1000)
            )
            boxSize.animateTo(
                targetValue = 360f,
                animationSpec = tween(1000)
            )



        delay(1500)
        launch {
            trashTrackStep = true
            dakantStep = true
            padding.animateTo(
                targetValue = 89f,
                animationSpec = tween(1000)
            )
        }
        delay(5000)
        allAnimationsFinished = true
    }

    DualAxisAnimationContent(
        timerStep = timerStep,
        listStep = listStep,
        frameStep = frameStep,
        trashTrackStep = trashTrackStep,
        circleStep = circleStep,
        dakantStep = dakantStep,
        boxSize = boxSize,
        boxOffsetY = boxOffsetY,
        point = point,
        points = points,
        padding = padding
    )
}
@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun DualAxisAnimationContent(
    point: List<Pair<Float, Float>>,
    points:List<Pair<Float, Float>>,
    padding: Animatable<Float, AnimationVector1D>,
    listStep: Boolean,
    timerStep: Boolean,
    frameStep: Boolean,
    trashTrackStep: Boolean,
    circleStep: Boolean,
    dakantStep: Boolean,
    boxSize: Animatable<Float, AnimationVector1D>,
    boxOffsetY: Animatable<Float, AnimationVector1D>
) {

    val colorLine by animateColorAsState(
        targetValue = if (listStep && frameStep) Color(0xFF006B05) else Color(0xFF404040),
        animationSpec = tween(1000)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = circleStep,
            enter = fadeIn() + expandVertically()
        ) {
            Box(
                modifier = Modifier
                    .offset(x = 0.dp, y = boxOffsetY.value.dp)
                    .size(boxSize.value.dp)
                    .border(width = 3.4.dp, color = Color(0xFF48934B), shape = CircleShape)
                    .padding(39.dp)
                    .border(width = 3.8.dp, color = Color(0xFF17781C), shape = CircleShape),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            AnimatedVisibility(
                visible = listStep
            ) {
                Frame(
                    points = point,
                    width = 25f,
                    maxHistorySize = 2000,
                    colorLine = colorLine
                )
            }
            AnimatedVisibility(
                visible = frameStep
            ) {
                Frame(
                    points = points,
                    width = 35f,
                    maxHistorySize = 1000,
                    colorLine = colorLine
                )
            }
            Content(
                dacantStep = dakantStep,
                timerStep = timerStep,
                padding = padding,
                trashTrackStep = trashTrackStep
            )
        }
    }
}

@Composable
fun Content(
    dacantStep: Boolean,
    timerStep: Boolean,
    padding: Animatable<Float, AnimationVector1D>,
    trashTrackStep: Boolean
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical =  31.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedVisibility(
            visible = trashTrackStep
        ) {
            AnimatedColorText(
                text = "TrashTrack",
                coloredParts = mapOf(
                    "Trash" to MaterialTheme.colors.black,
                    "Track" to MaterialTheme.colors.green700
                ),
                letterDelay = 150
            )
        }
        Spacer(modifier = Modifier.height(132.dp))
        TimerProgressBar(
            currentStep = timerStep,
            totalTime = 30000,
            maximumValue = true,
            percentage = 0f
        )
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedVisibility(
            visible = dacantStep,
            enter = fadeIn() + expandVertically()
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = padding.value.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "При поддерже",
                    style = TTTypography.titleLarge,
                    color = Color(0xFF525252)
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = "ДАКАНТ",
                    style = TTTypography.displayMedium,
                    color = Color(0xFF211600)
                )
            }
        }

    }
}


@Composable
fun AnimatedColorText(
    text: String,
    coloredParts: Map<String, Color> = emptyMap(),
    letterDelay: Long = 100
) {
    var visibleLetters by remember { mutableStateOf(0) }

    LaunchedEffect(text) {
        visibleLetters = 0
        while (visibleLetters < text.length) {
            delay(letterDelay)
            visibleLetters++
        }
    }

    val letterList = remember(text, coloredParts) {
        val result = mutableListOf<Pair<Char, Color?>>()
        var currentIndex = 0

        coloredParts.forEach { (word, color) ->
            val startIndex = text.indexOf(word, currentIndex)
            if (startIndex != -1) {
                text.substring(currentIndex, startIndex).forEach {
                    result.add(it to null)
                }
                word.forEach {
                    result.add(it to color)
                }
                currentIndex = startIndex + word.length
            }
        }
        text.substring(currentIndex).forEach {
            result.add(it to null)
        }
        result
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
        ) {
        letterList.forEachIndexed { index, (char, color) ->
            AnimatedVisibility(
                visible = index < visibleLetters,
                enter = fadeIn() + slideInVertically(
                    animationSpec = tween(durationMillis = 300),
                    initialOffsetY = { it / 2 }
                )
            ) {
                Text(
                    text = char.toString(),
                    color = color ?: LocalContentColor.current,
                    style = TTTypography.displaySmall,
                    modifier = Modifier
                )
            }
        }
    }
}