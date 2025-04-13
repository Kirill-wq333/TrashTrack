package com.example.trashtrack.ui.feature.presintation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trashtrack.mock.Mock
import com.example.trashtrack.ui.feature.presintation.alittlemore.ui.ALittleMoreScreen
import com.example.trashtrack.ui.feature.presintation.introduction.ui.IntroductionScreen
import com.example.trashtrack.ui.feature.presintation.splash.ui.DualAxisAnimationScreen

@Composable
fun NavigationBuilder(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "start"
    ) {

        composable(
            "start",
            exitTransition = { fadeOut(tween(1500)) }
        ) {
            DualAxisAnimationScreen(
                navController = navController
            )
        }

        composable(
            "aLittleMore",
            enterTransition = { fadeIn(tween(1500)) },
            exitTransition = { fadeOut(tween(1500)) }
            ) {
            ALittleMoreScreen(
                navController = navController
            )
        }

        composable(
            "introduction",
            exitTransition = { fadeOut(tween(1500)) }
        ) {
            IntroductionScreen(
                introduction = Mock.demoIntroduction,
            )
        }

        composable("user") {

        }
    }
}