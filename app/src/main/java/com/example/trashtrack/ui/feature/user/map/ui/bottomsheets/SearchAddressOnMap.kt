package com.example.trashtrack.ui.feature.user.map.ui.bottomsheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.button.TTBottom
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun SearchAddressOnMap(
    modifier: Modifier = Modifier,
    nextOpenData: () -> Unit
) {
    var address by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp)
    ) {
        Text(
            text = "Адрес, откуда заберем мусор",
            color = MaterialTheme.colors.neutral500,
            style = TTTypography.titleLarge
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            OutlinedTextFieldComponent(
                text = address,
                nameTextField = "Адрес",
                isErrorText = "Не указан номер дома !",
                onTextChange = { address = it },
                placeholder = {
                    Text(
                        text = "Введите адрес",
                        color = MaterialTheme.colors.neutral500,
                        style = TTTypography.titleLarge
                    )
                }
            )
            TTBottom(
                text = "Потвердить адрес",
                onClick = nextOpenData,
            )
        }
    }

}