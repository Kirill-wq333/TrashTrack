package com.example.trashtrack.ui.feature.user.introduction.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun ConfirmPasswordTextField(
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    passwordsMatch: Boolean
) {

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = { Text(
                text = "Подтвердите пароль",
                color = MaterialTheme.colors.neutral400,
                style = TTTypography.titleLarge,
            ) },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colors.black,
                unfocusedTextColor = MaterialTheme.colors.black,
                errorTextColor = MaterialTheme.colors.red600,
                focusedContainerColor = MaterialTheme.colors.white,
                unfocusedContainerColor = MaterialTheme.colors.white,
                errorContainerColor = MaterialTheme.colors.white,
                focusedIndicatorColor = MaterialTheme.colors.green600,
                cursorColor = MaterialTheme.colors.black
            ),
            modifier = Modifier.fillMaxWidth(),
            isError = !passwordsMatch && confirmPassword.isNotEmpty()
        )

        if (!passwordsMatch && confirmPassword.isNotEmpty()) {
            Text(
                text = "Пароли не совпадают!",
                color = MaterialTheme.colors.red600,
                style = TTTypography.titleLarge,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}