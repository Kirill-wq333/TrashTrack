package com.example.trashtrack.ui.feature.user.main.viewmodel

import com.example.trashtrack.core.viewmodel.ViewEffect
import com.example.trashtrack.core.viewmodel.ViewEvent
import com.example.trashtrack.core.viewmodel.ViewState

object MainContract {

    sealed interface Effect: ViewEffect{

    }

    sealed interface Event: ViewEvent{

    }

    sealed interface State: ViewState{
        data object LoadMainScreen: State
        data object PlaceOnOrder: State
    }

}