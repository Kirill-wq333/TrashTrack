package com.example.trashtrack.ui.feature.presintation.introduction.ui.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.feature.presintation.introduction.ui.components.OutlinedTextFieldComponent
import com.example.trashtrack.ui.feature.presintation.introduction.ui.components.PhoneTextField
import com.example.trashtrack.ui.feature.presintation.shared.checkbox.Checkbox


@Preview
@Composable
private fun RegistrationEmployeePreview() {
    Surface {
        RegistrationEmployee()
    }
}

@Composable
fun RegistrationEmployee(
    modifier: Modifier = Modifier
) {

    val isChecked = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        BackButton(
            backButton = {}
        )
        Spacer(modifier = Modifier.height(27.dp))
        HeadingAndUnderHeadingText(
            heading = "Создать учетную сотрудника"
        )
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
                openEntranceScreen = {},
            )
            Spacer(modifier = Modifier.height(11.dp))
            Services()
        }
        Spacer(modifier = Modifier.height(7.dp))
        RegistrationHowEmployee(
            openRegisterEmployeeScreen = {}
        )
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
private fun ColumnsTextField(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextFieldComponent(
            nameTextField = "Имя",
            isErrorText = "Заполните поле!",
        )
        OutlinedTextFieldComponent(
            nameTextField = "Email",
            isErrorText = "Неверная почта!",
        )
        PhoneTextField()
        OutlinedTextFieldComponent(
            nameTextField = "Номер пропуска",
            isErrorText = "Номер пропуска неверен!",
        )
        OutlinedTextFieldComponent(
            nameTextField = "Придуймайте пароль",
            isErrorText = "Пароль не надежен!",
        )
        OutlinedTextFieldComponent(
            nameTextField = "Повторите пароль",
            isErrorText = "Пароли не совпадают!",
        )
    }
}