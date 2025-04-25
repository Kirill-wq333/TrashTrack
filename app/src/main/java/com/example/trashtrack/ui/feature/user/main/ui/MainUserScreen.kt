package com.example.trashtrack.ui.feature.user.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trashtrack.mock.DataClasses
import com.example.trashtrack.ui.feature.user.main.ui.components.Cupon
import com.example.trashtrack.ui.feature.user.main.ui.components.NewsMain
import com.example.trashtrack.ui.feature.user.main.ui.components.PlaceAnOrder
import com.example.trashtrack.ui.feature.user.main.ui.components.Questions
import com.example.trashtrack.ui.feature.user.main.ui.components.ReceptionTime
import com.example.trashtrack.ui.feature.user.orders.PlaceAnOrderScreen

@Composable
fun MainUserScreen(
    onNewsClick: (DataClasses.NewsMain) -> Unit,
    newsMain: List<DataClasses.NewsMain>,
    color: Color
) {
    var currentScreenType by remember { mutableStateOf<MainType>(MainType.MainScreen) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {
        when (currentScreenType) {
            MainType.MainScreen -> {
                MainUserContent(
                    onNewsClick = onNewsClick,
                    newsMain = newsMain,
                    openPlaceAnOrder = { currentScreenType = MainType.PlaceAnOrder }
                )
            }

            MainType.PlaceAnOrder -> {
                PlaceAnOrderScreen(
                    backButton = { currentScreenType = MainType.MainScreen }
                )
            }
        }
    }
}

@Composable
fun MainUserContent(
    onNewsClick: (DataClasses.NewsMain) -> Unit,
    newsMain: List<DataClasses.NewsMain>,
    openPlaceAnOrder: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 38.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 33.dp),
            verticalArrangement = Arrangement.spacedBy(23.dp)
        ) {
            ReceptionTime()
            Cupon()
            PlaceAnOrder(
                openPlaceAnOrder = openPlaceAnOrder
            )
        }
        Spacer(modifier = Modifier.height(29.dp))
        NewsMain(
            newsMain = newsMain,
            onNewsClick = onNewsClick
        )
        Spacer(modifier = Modifier.height(18.dp))
        Questions()
    }
}