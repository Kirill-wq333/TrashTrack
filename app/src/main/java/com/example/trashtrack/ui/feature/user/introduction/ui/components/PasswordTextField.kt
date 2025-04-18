package com.example.trashtrack.ui.feature.user.introduction.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun PasswordTextField() {
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val passwordStrength = remember(password) {
        when {
            password.isEmpty() -> PasswordStrength.EMPTY
            password.length < 8 -> PasswordStrength.WEAK
            else -> PasswordStrength.STRONG
        }
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
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
                    onClick = { isPasswordVisible = !isPasswordVisible },
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
                    text = "Пароль надежный",
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

@Preview
@Composable
fun PasswordTextFieldPreview() {
    MaterialTheme {
        Surface {
            PasswordTextField()
        }
    }
}