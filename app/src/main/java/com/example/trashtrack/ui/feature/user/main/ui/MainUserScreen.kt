package com.example.trashtrack.ui.feature.user.main.ui

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.Text
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
import com.example.trashtrack.mock.Mock
import com.example.trashtrack.ui.feature.user.main.ui.components.Cupon
import com.example.trashtrack.ui.feature.user.main.ui.components.NewsMain
import com.example.trashtrack.ui.feature.user.main.ui.components.PlaceAnOrder
import com.example.trashtrack.ui.feature.user.main.ui.components.Questions
import com.example.trashtrack.ui.feature.user.main.ui.components.ReceptionTime
import com.example.trashtrack.ui.feature.user.map.ui.MapScreen
import com.example.trashtrack.ui.feature.user.map.ui.components.DetailsInformation
import com.example.trashtrack.ui.feature.user.map.ui.components.DetailsSubscription
import com.example.trashtrack.ui.feature.user.map.ui.components.SubscriptionCompleted
import com.example.trashtrack.ui.feature.user.orders.ui.PlaceAnOrderScreen

private interface MainCallback{
    fun openSubscriptionScreen(visibleBottomBar: Boolean)
    fun onNewsClick(news: DataClasses.NewsMain)
    fun onSubscriptionClick(visibleBottomBar: Boolean)
}

@Composable
fun MainUserScreen(
    onNewsClick: (DataClasses.NewsMain) -> Unit,
    newsMain: List<DataClasses.NewsMain>,
    color: Color,
    openSubscriptionScreen: (Boolean) -> Unit,
    onSubscriptionClick: (Boolean) -> Unit
) {

    val subscriptions = Mock.demoSubscriptionData

    val callback =
        object: MainCallback {
            override fun openSubscriptionScreen(visibleBottomBar: Boolean) = openSubscriptionScreen(visibleBottomBar)

            override fun onNewsClick(news: DataClasses.NewsMain) = onNewsClick(news)

            override fun onSubscriptionClick(visibleBottomBar: Boolean) = onSubscriptionClick(visibleBottomBar)
        }


    MainUserContent(
        callback = callback,
        color = color,
        newsMain = newsMain,
        subscriptions = subscriptions
    )
}

@Composable
private fun MainUserContent(
    callback: MainCallback,
    color: Color,
    newsMain: List<DataClasses.NewsMain>,
    subscriptions: List<DataClasses.SubscriptionData>
) {
    var selectedSubscriptionIndex by remember { mutableStateOf(-1) }
    val visibleBottomBar by remember { mutableStateOf(false) }

    var currentScreenType by remember { mutableStateOf<MainType>(MainType.MainScreen) }
    var subscriptionDetails by remember { mutableStateOf<DataClasses.SubscriptionDetails?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {
        when (currentScreenType) {
            MainType.MainScreen -> {
                MainUserContent(
                    onNewsClick = callback::onNewsClick,
                    newsMain = newsMain,
                    openPlaceAnOrder = { currentScreenType = MainType.PlaceAnOrder }
                )
            }

            MainType.PlaceAnOrder -> {
                PlaceAnOrderScreen(
                    subscription = subscriptions,
                    backButton = { currentScreenType = MainType.MainScreen },
                    onSubscriptionClick = { index ->
                        selectedSubscriptionIndex = index
                        currentScreenType = MainType.MapAddress
                        callback.onSubscriptionClick(visibleBottomBar)
                    }
                )
            }
            MainType.Data -> {
                DetailsInformation(
                    color = color,
                    backButton = { currentScreenType = MainType.MapAddress },
                    openDetailsSubscription = { currentScreenType = MainType.DetailsSubscription }
                )
            }

            MainType.DetailsSubscription -> {
                DetailsSubscription(
                    color = color,
                    subscription = subscriptions.getOrNull(selectedSubscriptionIndex),
                    backButton = { currentScreenType = MainType.Data },
                    openSubscriptionCompleted = { details ->
                        subscriptionDetails = details
                        currentScreenType = MainType.SubscriptionPayment
                    },
                )
            }
            MainType.MapAddress -> {
                MapScreen(
                    color = color,
                    nextOpenData = { currentScreenType = MainType.Data },
                    backButton = { currentScreenType = MainType.PlaceAnOrder }
                )
            }
            MainType.SubscriptionPayment -> {
                subscriptionDetails?.let { details ->
                    SubscriptionCompleted(
                        color = color,
                        subscriptionDetails = details,
                        openSubscriptionScreen = {
                            callback.openSubscriptionScreen(visibleBottomBar)
                        }
                    )
                } ?: run {
                    BackHandler { currentScreenType = MainType.DetailsSubscription }
                    Text("Ошибка: данные подписки не найдены")
                }
            }
        }
    }
}

@Composable
private fun MainUserContent(
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