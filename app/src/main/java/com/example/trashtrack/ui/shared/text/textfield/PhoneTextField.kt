package com.example.trashtrack.ui.shared.text.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.text.transform.rememberMaskVisualTransformation
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors


@Composable
fun PhoneTextField(
    phone: String,
    onPhoneChange: (String) -> Unit
) {
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            value = phone,
            onValueChange = {
                onPhoneChange(it)
                isError = phone.length < 11 && phone.isNotEmpty()
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colors.green600,
                unfocusedContainerColor = MaterialTheme.colors.white,
                focusedTextColor = MaterialTheme.colors.black,
                unfocusedTextColor = MaterialTheme.colors.black,
                focusedContainerColor = MaterialTheme.colors.white,
                errorContainerColor = MaterialTheme.colors.white,
                errorLabelColor = MaterialTheme.colors.red600,
                errorIndicatorColor = MaterialTheme.colors.red600,
                cursorColor = MaterialTheme.colors.black
            ),
            visualTransformation = rememberMaskVisualTransformation("+#(###)###-##-##"),
            label = {
                Text(
                    text = "Номер телефона",
                    color = MaterialTheme.colors.neutral400,
                    style = TTTypography.titleLarge,
                )
            },
            placeholder = { Text("+7(___)___-__-__") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone,),
            modifier = Modifier.fillMaxWidth()
        )

        if (isError) {
            Text(
                text = "Заполните поле!",
                color = MaterialTheme.colors.red600,
                style = TTTypography.titleLarge,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}

