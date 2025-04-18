package com.example.trashtrack.ui.feature.user.introduction.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun ComponentPreview() {
    Surface {
        OutlinedTextFieldComponent(
            nameTextField = "Имя",
            isErrorText = "Заполните текст!",
        )
    }
}

@Composable
fun OutlinedTextFieldComponent(
    nameTextField: String,
    isErrorText: String,
) {
    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = text,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colors.green600,
                unfocusedContainerColor = Color.White,
                focusedTextColor = MaterialTheme.colors.black,
                unfocusedTextColor = MaterialTheme.colors.black,
                focusedContainerColor = Color.White,
                errorContainerColor = Color.White,
                errorIndicatorColor = MaterialTheme.colors.red600
            ),
            onValueChange = {
                text = it
                isError = it.isEmpty()
            },
            label = {
                Text(
                    text = nameTextField,
                    color = MaterialTheme.colors.neutral400,
                    style = TTTypography.titleLarge,
                )
            },
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )

        if (isError) {
            Text(
                text = isErrorText,
                color = MaterialTheme.colors.red600,
                style = TTTypography.titleLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}