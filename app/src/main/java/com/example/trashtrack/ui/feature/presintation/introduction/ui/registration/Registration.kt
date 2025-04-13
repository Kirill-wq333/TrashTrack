package com.example.trashtrack.ui.feature.presintation.introduction.ui.registration

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashtrack.R
import com.example.trashtrack.ui.feature.presintation.introduction.ui.components.OutlinedTextFieldComponent
import com.example.trashtrack.ui.feature.presintation.introduction.ui.components.PhoneTextField
import com.example.trashtrack.ui.feature.presintation.shared.checkbox.Checkbox
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.spacers

@Preview
@Composable
private fun RegistrationPreview() {
    Surface {
        RegistrationContent(
            openEntranceScreen = {},
            openRegisterEmployeeScreen = {}
        )
    }
}

@Composable
fun RegistrationContent(
    openEntranceScreen: () -> Unit,
    openRegisterEmployeeScreen: () -> Unit
) {
    val isChecked = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadingAndUnderHeadingText()
        Spacer(modifier = Modifier.height(10.dp))
        ColumnsTextField()
        Spacer(modifier = Modifier.height(11.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Checkbox(
                modifier = Modifier
                    .padding(horizontal =  29.dp),
                title = "Я согласен с условиями предоставления услуг и политикой конфиденциальности»",
                isChecked = isChecked.value,
                onCheck = { isChecked.value = !isChecked.value }
            )
            Spacer(modifier = Modifier.height(23.dp))
            NextOrEntrance(
                modifier =Modifier
                    .padding(horizontal = 36.dp),
                openEntranceScreen = openEntranceScreen,
            )
            Spacer(modifier = Modifier.height(11.dp))
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
        }
        Spacer(modifier = Modifier.height(7.dp))
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
                    text = "Зарегистрироваться",
                    textDecoration = TextDecoration.Underline,
                    color = Color.Black,
                    style = TTTypography.titleLarge,
                    modifier = Modifier
                        .clickable(onClick = openRegisterEmployeeScreen)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = "как сотрудник",
                    color = Color.Black,
                    style = TTTypography.titleLarge,
                )
            }
        }

    }
}

@Composable
fun NextOrEntrance(
    modifier: Modifier = Modifier,
    openEntranceScreen: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {  })
                .background(
                    color = Color(0xFF16A34A),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Далее",
                color = Color.White,
                style = TTTypography.headlineLarge,
                modifier = Modifier
                    .padding(vertical = 13.dp)
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacers.small))

        Row {
            Text(
                text = "Или",
                color = Color.Black,
                style = TTTypography.titleLarge,
            )
            Spacer(Modifier.width(3.dp))
            Text(
                text = "войти",
                color = Color.Black,
                textDecoration = TextDecoration.Underline,
                style = TTTypography.titleLarge,
                modifier = Modifier.clickable(onClick = openEntranceScreen)
            )
        }

    }
}

@Composable
fun ColumnsTextField(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextFieldComponent(
            nameTextField = "Имя",
            isErrorText = "Заполните поле!"
        )
        OutlinedTextFieldComponent(
            nameTextField = "Email",
            isErrorText = "Неверная почта!"
        )
        PhoneTextField()
        OutlinedTextFieldComponent(
            nameTextField = "Придумайте пароль",
            isErrorText = "Пароль не надежен!"
        )
        OutlinedTextFieldComponent(
            nameTextField = "Повторите пароль",
            isErrorText = "Пароли не совпадают! "
        )

    }
}


@Composable
fun HeadingAndUnderHeadingText(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Создать учетную запись",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TTTypography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.small))
        Text(
            text = " Добро пожаловать! Пожалуйста, заполните форму ниже, чтобы создать новую учетную запись. Это займет всего несколько минут.",
            color = Color(0xFF737373),
            textAlign = TextAlign.Center,
            style = TTTypography.titleSmall,
        )
    }
}