package com.example.trashtrack.ui.feature.user.main.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun PlaceAnOrder() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 39.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Вывезим мусор из жилых помещений за вас",
            color = MaterialTheme.colors.black,
            style = TTTypography.headlineLarge
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colors.green600,
                    shape = RoundedCornerShape(12.dp)
                    ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Сделать заказ",
                color = MaterialTheme.colors.white,
                style = TTTypography.headlineLarge,
                modifier = Modifier
                    .padding(vertical = 13.dp)
            )
        }
    }
}