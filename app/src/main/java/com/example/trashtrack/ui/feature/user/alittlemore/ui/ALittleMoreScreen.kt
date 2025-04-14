package com.example.trashtrack.ui.feature.user.alittlemore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trashtrack.R
import com.example.trashtrack.ui.feature.user.splash.ui.components.TimerProgressBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ALittleMoreScreen(
    navController: NavHostController
) {

    var circularStep by remember { mutableStateOf(false) }
    var timerStep by remember { mutableStateOf(true) }
    var textStep by remember { mutableStateOf(false) }

    var allAnimationsFinished by remember { mutableStateOf(false) }

    LaunchedEffect(allAnimationsFinished) {
        delay(1000)
        if (allAnimationsFinished) {
            navController.navigate("introduction") {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }
    LaunchedEffect(timerStep) {
        launch {
            textStep = true
            timerStep = true
            circularStep = true
        }
        delay(1500)
        allAnimationsFinished = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF15803D)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier =Modifier
                .padding(bottom = 61.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier
                    .size(36.dp)
            )
            Spacer(modifier = Modifier.height(122.dp))
            TimerProgressBar(
                currentStep = true,
                totalTime = 30000,
                maximumValue = false,
                percentage = 0.8f
            )
            Spacer(modifier = Modifier.height(54.dp))
            Text(
                text = "Ещё немного...",
                color = Color.White,
                fontFamily = FontFamily(listOf(Font(R.font.manrope_extrabold))),
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}