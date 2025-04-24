package com.example.trashtrack.ui.feature.user.main.ui

sealed class MainType {
    data object MainScreen: MainType()
    data object PlaceAnOrder: MainType()
}