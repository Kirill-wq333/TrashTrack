package com.example.trashtrack.ui.feature.user.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trashtrack.mock.DataClasses
import com.example.trashtrack.mock.Mock
import com.example.trashtrack.ui.feature.user.main.ui.components.Cupon
import com.example.trashtrack.ui.feature.user.main.ui.components.NewsMain
import com.example.trashtrack.ui.feature.user.main.ui.components.PlaceAnOrder
import com.example.trashtrack.ui.feature.user.main.ui.components.Questions
import com.example.trashtrack.ui.feature.user.main.ui.components.ReceptionTime
import com.example.trashtrack.ui.theme.colors

@Composable
fun MainUserScreen(
    openNews: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.white)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 33.dp),
            verticalArrangement = Arrangement.spacedBy(23.dp)
        ) {
            ReceptionTime()
            Cupon()
            PlaceAnOrder()
        }
        Spacer(modifier = Modifier.height(29.dp))
        NewsMain(
            openNews = openNews,
            newsMain = Mock.demoNews
        )
        Spacer(modifier = Modifier.height(18.dp))
        Questions()
        Spacer(modifier = Modifier.height(38.dp))
    }
}