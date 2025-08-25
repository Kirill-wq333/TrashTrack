package com.example.trashtrack.ui.feature.user.splash.ui

import com.example.trashtrack.ui.feature.user.main.ui.MainType

sealed class SplashType {
    data object SplashScreen: SplashType()
    data object ALittleMoreScreen: SplashType()
}