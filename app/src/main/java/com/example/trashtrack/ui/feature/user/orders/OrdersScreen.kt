package com.example.trashtrack.ui.feature.user.orders

import android.R
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun OrdersPreview() {
    Surface {
        OrdersScreen()
    }
}

@Composable
fun OrdersScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val text = listOf("Активные", "История")

    Scaffold(
        topBar = {
            Text(
                text = "Мои заказы",
                color = MaterialTheme.colors.black,
                style = TTTypography.headlineLarge,
                modifier = Modifier
                    .padding(start = 30.dp, top = 39.dp)
            )
        }
    ) { paddingValue ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
        ) {
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
            Text(
                text = if (selectedTabIndex == 0) "Нет активных заказов" else "Нет выполненных заказов",
                color = MaterialTheme.colors.black,
                style = TTTypography.titleLarge,
                modifier = Modifier.align(Alignment.Center)
            )

        }
    }
}