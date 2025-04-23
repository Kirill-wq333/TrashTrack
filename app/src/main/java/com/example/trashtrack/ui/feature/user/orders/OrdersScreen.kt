package com.example.trashtrack.ui.feature.user.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trashtrack.ui.approuts.AppRoutes
import com.example.trashtrack.ui.feature.user.orders.components.FirstSubscription
import com.example.trashtrack.ui.feature.user.orders.components.OneTimeFee
import com.example.trashtrack.ui.feature.user.orders.components.Subscription
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun OrdersPreview() {
    Surface {
        PlaceAnOrderScreen(
            navController = rememberNavController()
        )
    }
}

@Preview
@Composable
private fun OrdersScreenPreview() {
    Surface {
        OrdersScreen(
            color = MaterialTheme.colors.white
        )
    }
}

@Composable
fun OrdersScreen(
    color: Color
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val text = listOf("Активные", "История")


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Мои заказы",
                color = MaterialTheme.colors.black,
                style = TTTypography.headlineLarge,
                modifier = Modifier
                    .padding(start = 30.dp, top = 39.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 38.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                text.forEachIndexed { index, i ->
                    Column(
                        modifier = Modifier
                            .width(157.dp)
                            .clickable(onClick = { selectedTabIndex = index }),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = i,
                            color = if (selectedTabIndex == index) MaterialTheme.colors.green600
                            else MaterialTheme.colors.neutral700,
                            style = TTTypography.titleMedium
                        )
                        HorizontalDivider(
                            color = if (selectedTabIndex == index) MaterialTheme.colors.green600
                            else MaterialTheme.colors.neutral400
                        )
                    }
                }
            }
        }
        Text(
            text = if (selectedTabIndex == 0) "Нет активных заказов" else "Нет выполненных заказов",
            color = MaterialTheme.colors.black,
            style = TTTypography.titleLarge,
            modifier = Modifier.align(Alignment.Center)
        )

    }
}

@Composable
fun PlaceAnOrderScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        BackButton(
            backButton = { navController.navigate(AppRoutes.USER) },
            paddingTop = 17.dp,
            paddingStart = 16.dp
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = "Заказ",
            color = MaterialTheme.colors.black,
            style = TTTypography.displaySmall,
            modifier = Modifier.padding(start = 25.dp)
        )
        Spacer(modifier = Modifier.height(9.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 9.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            OneTimeFee()
            FirstSubscription()
            Subscription(
                benefit = "Выгода до 40%",
                heading ="Подписка на  6 месяцев  " ,
                underHeading = " Оплати на 6 месяцев  по выгодной цене ,  и регулярный вывез мусора , либо по важему графику",
                money = "4000 ₽"
            )
            Subscription(
                benefit ="Выгода до 35%",
                heading = "Подписка на  1 год  ",
                underHeading = "Оплати на год  по выгодной цене , будет регулярный вывез мусора , либо по важему графику",
                money ="7000 ₽"
            )
        }
    }
}

