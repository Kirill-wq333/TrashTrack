package com.example.trashtrack.ui.feature.employee.introduction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.feature.user.introduction.ui.components.OutlinedTextFieldComponent
import com.example.trashtrack.ui.feature.user.introduction.ui.entrance.HeadingAndImage
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors
import com.example.trashtrack.ui.theme.spacers

@Composable
fun EntranceEmployee(
    modifier: Modifier = Modifier,
    openRegistrationEmployeeScreen: () -> Unit,
    backButton: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackButton(
            backButton = backButton
        )
        Spacer(modifier = Modifier.height(13.dp))
        HeadingAndImage()
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.medium))
        ColumnsTextFields(
            password = password,
            pass = pass,
            email = email,
            onPasswordChange = { password = it },
            onEmailChange = { email = it },
            onPassChange = { pass = it }

        )
        Spacer(modifier = Modifier.height(37.dp))
        TTButtonAndText()
        Spacer(modifier = Modifier.height(20.dp))
        Account(
            openRegistrationEmployeeScreen = openRegistrationEmployeeScreen
        )
        Spacer(modifier = Modifier.height(13.dp))
        Companion()
    }
}

@Composable
private fun BackButton(
    modifier: Modifier = Modifier,
    backButton: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 33.dp),
        contentAlignment = Alignment.CenterStart
    ){
        Box(
            modifier = Modifier
                .clickable(
                    onClick = backButton
                )
                .background(
                    color = Color(0xFFF6F6F5),
                    shape = RoundedCornerShape(6.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.left_chevron_button),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .padding(9.dp)
            )
        }
    }
}

@Composable
private fun ColumnsTextFields(
    modifier: Modifier = Modifier,
    password: String,
    pass: String,
    email: String,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit,

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        OutlinedTextFieldComponent(
            nameTextField = "Email",
            isErrorText = "Неверная почта!",
            text = email,
            onTextChange = onEmailChange
        )
        OutlinedTextFieldComponent(
            nameTextField = "Пароль",
            isErrorText = "Не правильно введен пароль!",
            text = password,
            onTextChange = onPasswordChange
        )
        OutlinedTextFieldComponent(
            nameTextField = "Номер пропуска",
            isErrorText = "Номер пропуска неверен!",
            text = pass,
            onTextChange = onPassChange
        )
    }
}

@Composable
fun Account(
    modifier: Modifier = Modifier,
    openRegistrationEmployeeScreen: () -> Unit
) {
    Row {
        Text(
            text = "У вас нету аккаунта?",
            color = Color.Black,
            style = TTTypography.titleLarge
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "Зарегистрируйтесь",
            color = Color.Black,
            textDecoration = TextDecoration.Underline,
            style = TTTypography.titleLarge,
            modifier = Modifier
                .clickable(onClick = openRegistrationEmployeeScreen)
        )
    }
}

@Composable
fun Companion(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(bottom = 36.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.icons_google),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Icon(
            painter = painterResource(R.drawable.vk),
            contentDescription = null,
            tint = Color.Blue.copy(.8f),
            modifier = Modifier
                .size(31.4.dp, 19.7.dp)
        )
    }
}

@Composable
private fun TTButtonAndText(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TTBottom()
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.small))
        Text(
            text = "Забыли пароль?",
            color = MaterialTheme.colors.neutral500,
            style = TTTypography.titleSmall,
        )
    }
}

@Composable
private fun TTBottom(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {})
            .background(
                color = Color(0xFF16A34A),
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Войти",
            color = Color.White,
            style = TTTypography.headlineLarge,
            modifier = Modifier
                .padding(vertical = 13.dp)
        )
    }
}