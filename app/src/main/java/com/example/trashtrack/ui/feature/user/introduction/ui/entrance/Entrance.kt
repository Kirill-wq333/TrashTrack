package com.example.trashtrack.ui.feature.user.introduction.ui.entrance

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.feature.user.introduction.ui.components.OutlinedTextFieldComponent
import com.example.trashtrack.ui.shared.button.TTBottom
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors
import com.example.trashtrack.ui.theme.spacers

@Preview
@Composable
private fun EntrancePreview() {
    Surface {
        EntranceContent(
            openEntranceScreen = {},
            openRegistrationScreen = {},
            openMainScreen = {},

        )
    }
}

@Composable
fun EntranceContent(
    openEntranceScreen: () -> Unit,
    openRegistrationScreen: () -> Unit,
    openMainScreen: () -> Unit,
) {
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadingAndImage()
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.medium))
        Column{
            OutlinedTextFieldComponent(
                nameTextField = "Email",
                isErrorText = "Неверная почта!",
                text = email,
                onTextChange = { email = it }
            )
            Spacer(modifier = Modifier.height(25.dp))
            OutlinedTextFieldComponent(
                nameTextField = "Пароль",
                isErrorText = "Пароль не надёжный!",
                text = password,
                onTextChange = { password = it }
            )
        }
        Spacer(modifier = Modifier.height(37.dp))
        TTButton(
            openMainScreen = openMainScreen,
            enable = true
        )
        Spacer(modifier = Modifier.height(30.2.dp))
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
                    .clickable(onClick = openRegistrationScreen)
            )
        }
        Spacer(modifier = Modifier.height(12.5.dp))
        Row {
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
        Spacer(modifier = Modifier.height(11.dp))
        Boxs(
            openEntranceScreen = openEntranceScreen
        )
    }
}

@Composable
private fun TTButton(
    openMainScreen: () -> Unit,
    enable: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TTBottom(
            onClick = openMainScreen,
            text = "Войти",
            enable = enable,
            color = MaterialTheme.colors.green600
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.small))
        Text(
            text = "Забыли пароль?",
            color = MaterialTheme.colors.neutral500,
            style = TTTypography.titleSmall,
        )
    }
}

@Composable
private fun Boxs(
    openEntranceScreen: () -> Unit
){
    Box(
        modifier = Modifier
            .padding(bottom = 42.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
    ){
        Row(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    start = 10.dp,
                    bottom = 7.dp,
                    end = 10.dp
                )
        ) {
            Text(
                text = "Войти",
                textDecoration = TextDecoration.Underline,
                color = Color.Black,
                style = TTTypography.titleLarge,
                modifier = Modifier
                    .clickable(onClick = openEntranceScreen)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "как сотрудник",
                color = Color.Black,
                style = TTTypography.titleLarge
            )
        }
    }
}

@Composable
fun HeadingAndImage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        Text(
            text = "Вход в систему",
            color = Color.Black,
            style = TTTypography.headlineLarge
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.scale_12005),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}