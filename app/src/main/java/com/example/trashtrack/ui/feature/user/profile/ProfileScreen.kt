package com.example.trashtrack.ui.feature.user.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

data class ProfileTextAndIconItem(
    val colorText: Color,
    val colorIcon: Color,
    val text: String,
    val icon: Int,
    val onClick: () -> Unit,

)


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val profileItem = listOf(
        ProfileTextAndIconItem(
            colorIcon = MaterialTheme.colors.green600,
            colorText = MaterialTheme.colors.black,
            text = "Мои данные",
            icon = TODO(),
            onClick = {}
        ),
        ProfileTextAndIconItem(
            colorIcon = MaterialTheme.colors.green600,
            colorText = MaterialTheme.colors.black,
            text = "Мои подписки",
            icon = TODO(),
            onClick = {}
        ),
        ProfileTextAndIconItem(
            colorIcon = MaterialTheme.colors.green600,
            colorText = MaterialTheme.colors.black,
            text = "Выйти из приложения",
            icon = TODO(),
            onClick = {}
        ),
        ProfileTextAndIconItem(
            colorIcon = MaterialTheme.colors.red600,
            colorText = MaterialTheme.colors.red600,
            text = "Удалить аккаунт",
            icon = TODO(),
            onClick = {}
        ),
    )

    Scaffold(
        topBar = {
            Text(
                text = "Добрый день",
                color = MaterialTheme.colors.black,
                style = TTTypography.displaySmall,
                modifier = Modifier
                    .padding(top = 39.dp, start = 30.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .padding(start = 32.dp),
                verticalArrangement = Arrangement.spacedBy(39.dp)
            ) {
                Row {

                }
            }
        }
    }

}