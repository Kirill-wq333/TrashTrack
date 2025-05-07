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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.trashtrack.mock.Mock
import com.example.trashtrack.ui.approuts.AppRoutes
import com.example.trashtrack.ui.feature.user.alittlemore.ui.ALittleMoreScreen
import com.example.trashtrack.ui.feature.user.introduction.ui.IntroductionScreen
import com.example.trashtrack.ui.feature.user.main.ui.MainUserScreen
import com.example.trashtrack.ui.feature.user.news.ui.NewsScreen
import com.example.trashtrack.ui.feature.user.orders.OrdersScreen
import com.example.trashtrack.ui.feature.user.profile.ProfileScreen
import com.example.trashtrack.ui.feature.user.splash.ui.DualAxisAnimationScreen
import com.example.trashtrack.ui.theme.colors

@Composable
fun NavigationBuilder(
    navController: NavHostController,
    setVisibleBottomBarUser: (Boolean) -> Unit,
    paddingValues: PaddingValues
) {
    val mockNews = Mock.demoNews
    val colorFrame = MaterialTheme.colors.white

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
                navController = navController,
                color = colorFrame
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

        composable(route = AppRoutes.INTRODUCTION) {
            IntroductionScreen(
                introduction = Mock.demoIntroduction,
                openMainScreen = {
                    navController.navigate(AppRoutes.USER)
                    setVisibleBottomBarUser(true)
                },
                color = colorFrame
            )
        }

        composable(route = AppRoutes.USER) {
            MainUserScreen(
                onNewsClick = { newsItem ->
                    navController.navigate("${AppRoutes.NEWS}/${newsItem.id}")
                },
                newsMain = mockNews,
                color = colorFrame
            )
        }

        composable(
            route = "${AppRoutes.NEWS}/{newsId}",
            arguments = listOf(navArgument("newsId") { type = NavType.IntType })
        ) { backStackEntry ->
            val newsId = backStackEntry.arguments?.getInt("newsId") ?: return@composable

            NewsScreen(
                newsId = newsId,
                news = mockNews,
                backButton = { navController.popBackStack() },
                navigateToNews = { id ->
                    navController.navigate("${AppRoutes.NEWS}/$id")
                },
                color = colorFrame
            )
        }
        composable(route = AppRoutes.PROFILE_USER) {
            ProfileScreen(
                navController = navController,
                color = colorFrame
            )
        }

        composable(AppRoutes.ORDERS) {
            OrdersScreen(color = colorFrame)
        }

    }
}