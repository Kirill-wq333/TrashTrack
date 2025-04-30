package com.example.trashtrack.ui.feature.user.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent
import com.example.trashtrack.ui.shared.text.textfield.PhoneTextField
import com.example.trashtrack.ui.shared.button.TTBottom
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun DataScreen(
    modifier: Modifier = Modifier,
    backButton: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("7") }

    Content(
        name = name,
        phone = phone,
        email = email,
        onChangeName = { name = it },
        onChangeEmail = { email = it },
        onChangePhone = { phone = it },
        backButton = backButton
    )
}

@Composable
fun Content(
    backButton: () -> Unit,
    name: String,
    phone: String,
    email: String,
    onChangePhone: (String) -> Unit,
    onChangeName: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 42.dp,
                    start = 25.dp,
                    bottom = 47.dp,
                    end = 20.dp
                ),
            horizontalAlignment = Alignment.Start
        ) {
            BackButton(
                backButton = backButton,
                paddingStart = 0.dp,
                color = MaterialTheme.colors.neutral300
            )
            Spacer(modifier = Modifier.height(43.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Text(
                    text = "Мои данные",
                    color = MaterialTheme.colors.black,
                    style = TTTypography.displaySmall
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    OutlinedTextFieldComponent(
                        nameTextField = "Имя",
                        isErrorText = "Заполните поле!",
                        onTextChange = onChangeName,
                        text = name
                    )
                    PhoneTextField(
                        phone = phone,
                        onPhoneChange = onChangePhone,
                    )
                    OutlinedTextFieldComponent(
                        nameTextField = "Email",
                        isErrorText = "Неверная почта!",
                        text = email,
                        onTextChange = onChangeEmail
                    )
                }
            }
        }


        TTBottom(
            modifier = Modifier
                .padding(horizontal = 25.dp),
            text = "Сохранить",
            onClick = {},
            color = MaterialTheme.colors.green600
        )
    }
}