package com.example.trashtrack.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trashtrack.mock.Mock
import com.example.trashtrack.ui.feature.user.alittlemore.ui.ALittleMoreScreen
import com.example.trashtrack.ui.feature.user.introduction.ui.IntroductionScreen
import com.example.trashtrack.ui.feature.user.main.ui.MainUserScreen
import com.example.trashtrack.ui.feature.user.news.ui.NewsScreen
import com.example.trashtrack.ui.feature.user.splash.ui.DualAxisAnimationScreen

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

        composable("introduction",) {
            IntroductionScreen(
                introduction = Mock.demoIntroduction,
                openMainScreen = { navController.navigate("user") }
            )
        }

        composable("user") {
            MainUserScreen(
                openNews = { navController.navigate("news") }
            )
        }

        composable("news") {
            NewsScreen(
                news = Mock.demoNews,
                backButton = { navController.navigate("user") }
            )
        }
    }
}