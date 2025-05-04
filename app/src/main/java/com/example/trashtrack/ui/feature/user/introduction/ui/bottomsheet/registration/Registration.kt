package com.example.trashtrack.ui.feature.user.introduction.ui.bottomsheet.registration

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.feature.user.introduction.ui.components.ConfirmPasswordTextField
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent
import com.example.trashtrack.ui.shared.text.textfield.PasswordTextField
import com.example.trashtrack.ui.shared.text.textfield.PhoneTextField
//import com.example.trashtrack.ui.preferences.SecurePrefsHelper
import com.example.trashtrack.ui.shared.button.TTBottom
import com.example.trashtrack.ui.shared.checkbox.Checkbox
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors
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
    val context = LocalContext.current

    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("7") }
    var email by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val passwordsMatch = remember(password, confirmPassword) { password == confirmPassword }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isChecked = remember { mutableStateOf(false) }

//    val securePrefsHelper = remember { SecurePrefsHelper.getInstance(context) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadingAndUnderHeadingText(
            heading = "Создать учетную запись"
        )
        Spacer(modifier = Modifier.height(10.dp))
        ColumnsTextField(
            password = password,
            phone = phone,
            name = name,
            email = email,
            onPhoneChange = { phone = it.take(11) },
            onEmailChange = { email = it },
            onNameChange = { name = it },
            confirmPassword = confirmPassword,
            onPasswordChange = { password = it },
            onVisibilityChange = { isPasswordVisible = !isPasswordVisible },
            onConfirmPasswordChange = { confirmPassword = it },
            isPasswordVisible = isPasswordVisible,
            passwordsMatch = passwordsMatch
        )

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
                enable = passwordsMatch && password.isNotEmpty() && confirmPassword.isNotEmpty(),
                onClickNext = {
//                    if (isChecked.value) {
//                        val success = securePrefsHelper.saveUserData(
//                            email = email,
//                            password = password,
//                            name = name,
//                            phone = phone
//                        )
//                    }
                }
            )
            Spacer(modifier = Modifier.height(11.dp))
            Services()
        }
        Spacer(modifier = Modifier.height(7.dp))
        RegistrationHowEmployee(
            openScreen = openRegisterEmployeeScreen,
            text = "Зарегистрироваться"
        )
    }
}

@Composable
fun NextOrEntrance(
    modifier: Modifier = Modifier,
    openEntranceScreen: () -> Unit,
    onClickNext: () -> Unit,
    enable: Boolean
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TTBottom(
            text = "Далее",
            onClick = onClickNext,
            color = MaterialTheme.colors.green600,
            enable = enable
        )

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
private fun ColumnsTextField(
    modifier: Modifier = Modifier,
    password: String,
    confirmPassword: String,
    phone: String,
    name: String,
    email: String,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    passwordsMatch: Boolean,
    onVisibilityChange: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextFieldComponent(
            nameTextField = "Имя",
            isErrorText = "Заполните поле!",
            onTextChange = onNameChange,
            text = name
        )
        OutlinedTextFieldComponent(
            nameTextField = "Email",
            isErrorText = "Неверная почта!",
            text = email,
            onTextChange = onEmailChange
        )
        PhoneTextField(
            phone = phone,
            onPhoneChange = onPhoneChange
        )
        PasswordTextField(
            password = password,
            onPasswordChange = onPasswordChange,
            isPasswordVisible = isPasswordVisible,
            onVisibilityChange = onVisibilityChange
        )
        ConfirmPasswordTextField(
            confirmPassword = confirmPassword,
            passwordsMatch = passwordsMatch,
            onConfirmPasswordChange = onConfirmPasswordChange
        )

    }
}


@Composable
fun HeadingAndUnderHeadingText(
    modifier: Modifier = Modifier,
    heading: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = heading,
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

@Composable
fun Services(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_google),
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
fun RegistrationHowEmployee(
    modifier: Modifier = Modifier,
    openScreen: () -> Unit,
    text: String
) {
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
                text = text,
                textDecoration = TextDecoration.Underline,
                color = Color.Black,
                style = TTTypography.titleLarge,
                modifier = Modifier
                    .clickable(onClick = openScreen)
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