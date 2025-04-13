package com.example.trashtrack.ui.feature.presintation.introduction.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashtrack.R
import com.example.trashtrack.ui.feature.presintation.shared.text.transform.rememberMaskVisualTransformation
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun PhonePreview() {
    Surface {
        MaterialTheme {
            PhoneTextField()
        }
    }
}

@Composable
fun PhoneTextField() {
    var phone by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it.take(10)
                isError = phone.length < 10 && phone.isNotEmpty()
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colors.green600,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                errorContainerColor = Color.White,
                errorLabelColor = MaterialTheme.colors.red600,
                errorIndicatorColor = MaterialTheme.colors.red600
            ),
            visualTransformation = rememberMaskVisualTransformation("+7(###)###-##-##"),
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
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

