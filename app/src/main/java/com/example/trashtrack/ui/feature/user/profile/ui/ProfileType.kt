package com.example.trashtrack.ui.feature.user.profile.ui

sealed class ProfileType {
    data object MainProfile: ProfileType()
    data object DataProfile: ProfileType()
    data object SubscriptionProfile: ProfileType()
}