package com.example.trashtrack.ui.feature.user.orders.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun ColumnPreview() {
    Surface(color = Color.Blue) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Subscription(
                benefit = "",
                heading = "Разовый взнос 150 Р",
                underHeading = "",
                money = 0,
                price = 0,
                openMapScreen = {},
                visibleMoneyAndPrice = false
            )
            Subscription(
                benefit = "klsdgjklsg",
                heading = "Разовый взнос 150 Р",
                underHeading = "ntgkj",
                money = 200,
                price = 1500,
                openMapScreen = {},
                visibleMoneyAndPrice = true
            )
            Subscription(
                benefit = "Выгода до 40%",
                heading = "Подписка на  6 месяцев  ",
                underHeading = "Оплати на 6 месяцев  по выгодной цене ,  и регулярный вывез мусора , либо по важему графику",
                money = 4000,
                price = 0,
                openMapScreen = {},
                visibleMoneyAndPrice = true
            )
            Subscription(
                benefit ="Выгода до 35%",
                heading = "Подписка на  1 год  ",
                underHeading = "Оплати на год  по выгодной цене , будет регулярный вывез мусора , либо по важему графику",
                money = 7000,
                price = 0,
                openMapScreen = {},
                visibleMoneyAndPrice = true
            )
        }
    }
}

@Composable
fun Subscription(
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = MaterialTheme.colors.neutral500,
    colorText: Color = MaterialTheme.colors.white,
    benefit: String,
    heading: String,
    underHeading: String,
    money: Int,
    price: Int,
    visibleMoneyAndPrice: Boolean,
    openMapScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = openMapScreen
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
                )
    ){
        ComponentsSubscription(
            benefit = benefit,
            heading = heading,
            underHeading = underHeading,
            money = money,
            price = price,
            colorText = colorText,
            visibleMoneyAndPrice = visibleMoneyAndPrice
        )
    }
}

@Composable
fun ComponentsSubscription(
    benefit: String,
    heading: String,
    money: Int,
    underHeading: String,
    price: Int,
    colorText: Color,
    visibleMoneyAndPrice: Boolean
) {
    Column(
        modifier = Modifier
            .padding(
                top = if (visibleMoneyAndPrice) 10.dp else 13.dp,
                start = if (visibleMoneyAndPrice) 10.dp else 20.dp,
                bottom = if(visibleMoneyAndPrice) 26.dp else 13.dp,
                end = if (visibleMoneyAndPrice) 26.dp else 0.dp
            )
    ) {
        Text(
            text = heading,
            color = colorText,
            style = TTTypography.displaySmall
        )
        if (visibleMoneyAndPrice) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = underHeading,
            color = colorText,
            style = TTTypography.bodyMedium
        )
            Spacer(modifier = Modifier.height(36.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (money > 0 && price <= 0) {
                    Text(
                        text = stringResource(R.string.ruble, money),
                        color = MaterialTheme.colors.neutral950,
                        style = TTTypography.headlineSmall
                    )
                } else{
                    Text(
                        text = benefit,
                        color = colorText,
                        style = TTTypography.headlineSmall
                    )
                }
                if (money > 0 && price > 0) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.ruble, price),
                            color = colorText,
                            style = TTTypography.titleMedium,
                            textDecoration = TextDecoration.LineThrough
                        )
                        Box(
                            modifier = Modifier
                                .background(
                                    color = colorText,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            Text(
                                text = stringResource(R.string.ruble, money),
                                color = MaterialTheme.colors.neutral950,
                                style = TTTypography.titleMedium,
                                modifier = Modifier
                                    .padding(vertical = 8.dp, horizontal = 15.dp)
                            )
                        }
                    }
                } else {
                    Text(
                        text = benefit,
                        color = MaterialTheme.colors.green700,
                        style = TTTypography.headlineSmall
                    )
                }
            }
        }
    }
}