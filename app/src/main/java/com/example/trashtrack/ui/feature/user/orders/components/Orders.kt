package com.example.trashtrack.ui.feature.user.orders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun ColumnPreview() {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
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

@Composable
fun OneTimeFee(
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = MaterialTheme.colors.neutral500
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            text = "Разовый взнос 150 ₽",
            color = MaterialTheme.colors.black,
            style = TTTypography.headlineLarge,
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    top = 13.dp,
                    bottom = 13.dp
                )
        )
    }
}

@Composable
fun FirstSubscription(
    borderColor: Color = MaterialTheme.colors.green500,
    backgroundColor: Color = MaterialTheme.colors.green600,
    colorText: Color = MaterialTheme.colors.white
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ){
        Column(
            modifier = Modifier
                .padding(
                    top = 12.dp,
                    start = 17.dp,
                    bottom = 14.dp,
                    end = 10.dp
                )
        ) {
            Text(
                text = "Первая подписка",
                color = colorText,
                style = TTTypography.displaySmall
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "1 месяц вывез мусора,каждый понедельник",
                color = colorText,
                style = TTTypography.bodyMedium
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Выгода 80%",
                    color = colorText,
                    style = TTTypography.headlineSmall
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "1500 ₽",
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
                            text = "200 ₽",
                            color = MaterialTheme.colors.neutral950,
                            style = TTTypography.titleMedium,
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 15.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Subscription(
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = MaterialTheme.colors.neutral500,
    benefit: String,
    heading: String,
    underHeading: String,
    money: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
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
            money = money
        )
    }
}

@Composable
fun ComponentsSubscription(
    benefit: String,
    heading: String,
    money: String,
    underHeading: String
) {
    Column(
        modifier = Modifier
            .padding(
                top = 10.dp,
                start = 10.dp,
                bottom = 26.dp,
                end = 26.dp
            )
    ) {
        Text(
            text = heading,
            color = MaterialTheme.colors.neutral950,
            style = TTTypography.displaySmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = underHeading,
            color = MaterialTheme.colors.primary600,
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
            Text(
                text = money,
                color = MaterialTheme.colors.neutral950,
                style = TTTypography.headlineSmall
            )
            Text(
                text = benefit,
                color = MaterialTheme.colors.green700,
                style = TTTypography.headlineSmall
            )
        }
    }
}