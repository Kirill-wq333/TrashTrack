package com.example.trashtrack.ui.feature.employee.introduction.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.feature.user.introduction.ui.components.ConfirmPasswordTextField
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent
import com.example.trashtrack.ui.shared.text.textfield.PasswordTextField
import com.example.trashtrack.ui.shared.text.textfield.PhoneTextField
import com.example.trashtrack.ui.feature.user.introduction.ui.bottomsheet.registration.HeadingAndUnderHeadingText
import com.example.trashtrack.ui.feature.user.introduction.ui.bottomsheet.registration.NextOrEntrance
import com.example.trashtrack.ui.feature.user.introduction.ui.bottomsheet.registration.RegistrationHowEmployee
import com.example.trashtrack.ui.feature.user.introduction.ui.bottomsheet.registration.Services
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.shared.checkbox.Checkbox


@Preview
@Composable
private fun RegistrationEmployeePreview() {
    Surface {
        RegistrationEmployeeScreen(
            openEntranceScreen = {},
            openEntranceEmployeeScreen = {},
            backButton = {}
        )
    }
}

@Composable
fun RegistrationEmployeeScreen(
    openEntranceScreen: () -> Unit,
    openEntranceEmployeeScreen: () -> Unit,
    backButton: () -> Unit,
) {
    var password by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("7") }
    var confirmPassword by remember { mutableStateOf("") }
    val passwordsMatch = remember(password, confirmPassword) { password == confirmPassword }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isChecked = remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        BackButton(
            backButton = backButton
        )
        Spacer(modifier = Modifier.height(27.dp))
        HeadingAndUnderHeadingText(
            heading = "Создать учетную сотрудника"
        )
        Spacer(modifier = Modifier.height(10.dp))
        ColumnsTextField(
            password = password,
            confirmPassword = confirmPassword,
            pass = pass,
            phone = phone,
            name = name,
            email = email,
            onPhoneChange = { phone = it.take(11) },
            onPasswordChange = { password = it },
            onNameChange = { name = it },
            onEmailChange = { email = it },
            onPassChange = { pass = it },
            onConfirmPasswordChange = { confirmPassword = it },
            passwordsMatch = passwordsMatch,
            isPasswordVisible = isPasswordVisible,
            onVisibilityChange = { isPasswordVisible = !isPasswordVisible },

        )
        Spacer(modifier = Modifier.height(11.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Checkbox(
                modifier = Modifier
                    .padding(horizontal = 29.dp),
                title = "Я согласен с условиями предоставления услуг и политикой конфиденциальности»",
                isChecked = isChecked.value,
                onCheck = { isChecked.value = !isChecked.value }
            )
            Spacer(modifier = Modifier.height(23.dp))
            NextOrEntrance(
                modifier = Modifier
                    .padding(horizontal = 36.dp),
                openEntranceScreen = openEntranceScreen,
                enable = passwordsMatch && password.isNotEmpty() && confirmPassword.isNotEmpty(),
                onClickNext = {}
            )
            Spacer(modifier = Modifier.height(11.dp))
            Services()

            Spacer(modifier = Modifier.height(7.dp))
            RegistrationHowEmployee(
                openScreen = openEntranceEmployeeScreen,
                text = "Войти"
            )
        }
    }
}

@Composable
private fun ColumnsTextField(
    password: String,
    confirmPassword: String,
    phone: String,
    name: String,
    email: String,
    pass: String,
    onPhoneChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onVisibilityChange: (Boolean) -> Unit,
    passwordsMatch: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
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
        OutlinedTextFieldComponent(
            nameTextField = "Номер пропуска",
            isErrorText = "Номер пропуска неверен!",
            onTextChange = onPassChange,
            text = pass
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