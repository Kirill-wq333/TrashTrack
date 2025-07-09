package com.example.trashtrack.ui.feature.user.main.ui

sealed class MainType {
    data object MainScreen: MainType()
    data object PlaceAnOrder: MainType()
    data object MapAddress: MainType()
    data object Data: MainType()
    data object DetailsSubscription: MainType()
    data object SubscriptionPayment: MainType()
}