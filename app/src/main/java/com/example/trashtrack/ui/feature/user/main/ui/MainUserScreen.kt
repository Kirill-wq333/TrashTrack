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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.trashtrack.ui.feature.user.map.ui.MapScreen
import com.example.trashtrack.ui.feature.user.map.ui.components.DetailsInformation
import com.example.trashtrack.ui.feature.user.map.ui.components.DetailsSubscription
import com.example.trashtrack.ui.feature.user.map.ui.components.SubscriptionCompleted
import com.example.trashtrack.ui.feature.user.orders.PlaceAnOrderScreen
import com.example.trashtrack.ui.theme.colors

data class SubscriptionData(
    val benefit: String,
    val heading: String,
    val underHeading: String,
    val money: Int,
    val price: Int,
    val visible: Boolean,
    val background: Color,
    val border: Color,
    val text: Color
)

data class SubscriptionDetails(
    val date: String,
    val time: String,
    val comment: String,
    val subscriptionData: SubscriptionData?
)

@Composable
fun MainUserScreen(
    onNewsClick: (DataClasses.NewsMain) -> Unit,
    newsMain: List<DataClasses.NewsMain>,
    color: Color,
    openSubscriptionScreen: () -> Unit,
    visibleBottomBar: (Boolean) -> Unit,
    visibleProfileBottomBar: (Boolean) -> Unit
) {

    val subscriptions = remember {
        val white = Color(0xFFFFFFFF)
        val neutral950 = Color(0xFF0A0A0A)
        val neutral500 = Color(0xFF737373)
        val green600 = Color(0xFF16A34A)
        val green500 = Color(0xFF22C55E)

        mutableStateListOf(
            SubscriptionData(
                benefit = "",
                heading = "Разовый взнос 150 ₽",
                underHeading = "",
                money = 0,
                price = 0,
                visible = false,
                background = white,
                border = neutral500,
                text = neutral950,
            ),
            SubscriptionData(
                benefit = "Выгода 80%",
                heading = "Первая подписка",
                underHeading = "1 месяц вывез мусора, каждый понедельник",
                money = 200,
                price = 1500,
                visible = true,
                background = green600,
                border = green500,
                text = white,
            ),
            SubscriptionData(
                benefit = "Выгода до 40%",
                heading = "Подписка на 6 месяцев",
                underHeading = "Оплата на 6 месяцев по выгодной цене, и регулярный вывез мусора, либо по вашему графику",
                price = 0,
                money = 7000,
                visible = true,
                background = white,
                border = neutral500,
                text = neutral950,
            ),
            SubscriptionData(
                benefit = "Выгода до 35%",
                heading = "Подписка на 1 год",
                underHeading = "Оплата на год по выгодной цене, и регулярный вывез мусора, либо по вашему графику",
                price = 0,
                money = 7000,
                visible = true,
                background = white,
                border = neutral500,
                text = neutral950,
            ),
        )
    }

    var selectedSubscriptionIndex by remember { mutableStateOf(-1) }

    var currentScreenType by remember { mutableStateOf<MainType>(MainType.MainScreen) }
    var subscriptionDetails by remember { mutableStateOf<SubscriptionDetails?>(null) }

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
                    subscription = subscriptions,
                    backButton = { currentScreenType = MainType.MainScreen },
                    onSubscriptionClick = { index ->
                        selectedSubscriptionIndex = index
                        currentScreenType = MainType.MapAddress
                        println("currentScreenType set to: $currentScreenType")
                        visibleBottomBar(false)
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
                            openSubscriptionScreen()
                            visibleProfileBottomBar(true)
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