package com.example.trashtrack.ui.shared.text.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onVisibilityChange: (Boolean) -> Unit
) {

    val passwordStrength = remember(password) {
        when {
            password.isEmpty() -> PasswordStrength.EMPTY
            password.length < 8 -> PasswordStrength.WEAK
            else -> PasswordStrength.STRONG
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(
                text = "Пароль",
                color = MaterialTheme.colors.neutral400,
                style = TTTypography.titleLarge,
            ) },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(
                    onClick = { onVisibilityChange(isPasswordVisible) },
                    ) {
                    Icon(
                        painter = if (isPasswordVisible) painterResource(R.drawable.eye)
                                        else painterResource(R.drawable.blind),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
            },
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
            isError = passwordStrength == PasswordStrength.WEAK && password.isNotEmpty(),
        )

        when (passwordStrength) {
            PasswordStrength.WEAK -> {
                Text(
                    text = "Пароль должен содержать минимум 8 символов",
                    color = MaterialTheme.colors.red600,
                    style = TTTypography.titleLarge,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
            PasswordStrength.STRONG -> {
                Text(
                    text = "Пароль совпадает",
                    color = MaterialTheme.colors.green600,
                    style = TTTypography.titleLarge,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
            else -> Unit
        }
    }
}

private enum class PasswordStrength {
    EMPTY, WEAK, STRONG
}
