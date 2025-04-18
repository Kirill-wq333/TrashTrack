package com.example.trashtrack.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trashtrack.mock.Mock
import com.example.trashtrack.ui.approuts.AppRoutes
import com.example.trashtrack.ui.feature.user.alittlemore.ui.ALittleMoreScreen
import com.example.trashtrack.ui.feature.user.introduction.ui.IntroductionScreen
import com.example.trashtrack.ui.feature.user.main.ui.MainUserScreen
import com.example.trashtrack.ui.feature.user.news.ui.NewsScreen
import com.example.trashtrack.ui.feature.user.orders.OrdersScreen
import com.example.trashtrack.ui.feature.user.splash.ui.DualAxisAnimationScreen
import com.example.trashtrack.ui.theme.colors

@Composable
fun NavigationBuilder(
    navController: NavHostController,
    setVisibleBottomBarUser: (Boolean) -> Unit,
    setVisibleBottomBarEmployee: (Boolean) -> Unit,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.START,
        modifier = Modifier
            .padding(paddingValues)
            .background(MaterialTheme.colors.white)
    ) {

        composable(
            route = AppRoutes.START,
            exitTransition = { fadeOut(tween(1500)) }
        ) {
            DualAxisAnimationScreen(
                navController = navController
            )
        }

        composable(
            route = AppRoutes.A_LITTLE_MORE,
            enterTransition = { fadeIn(tween(1500)) },
            exitTransition = { fadeOut(tween(1500)) }
            ) {
            ALittleMoreScreen(
                navController = navController
            )
        }

        composable(route = AppRoutes.INTRODUCTION,) {
            IntroductionScreen(
                introduction = Mock.demoIntroduction,
                openMainScreen = {
                    navController.navigate("user");
                    setVisibleBottomBarUser(true)
                }
            )
        }

        composable(route = AppRoutes.USER) {
            MainUserScreen(
                openNews = { navController.navigate("news") }
            )
        }

        composable(route = AppRoutes.NEWS) {
            NewsScreen(
                news = Mock.demoNews,
                backButton = { navController.navigate("user") }
            )
        }

        composable(route = AppRoutes.PROFILE_USER) {

        }

        composable(AppRoutes.ORDERS) {
            OrdersScreen()
        }
    }
}