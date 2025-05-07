package com.example.trashtrack.ui.feature.employee.introduction.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.feature.user.introduction.ui.bottomsheet.entrance.EntranceContent
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent

@Preview
@Composable
private fun EntranceEmployeePreview() {
    Surface {
        EntranceEmployee(
            openRegistrationEmployeeScreen = {},
            backButton = {}
        )
    }
}

@Composable
fun EntranceEmployee(
    openRegistrationEmployeeScreen: () -> Unit,
    backButton: () -> Unit
) {
    var pass by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(13.dp),
        horizontalAlignment = Alignment.Start
    ) {
        BackButton(
            backButton = backButton,
        )
        EntranceContent(
            openRegistrationScreen = openRegistrationEmployeeScreen,
            openScreen = {},
            visibleBoxs = false,
            visibleContent = true,
            content = {
                OutlinedTextFieldComponent(
                    nameTextField = "Номер пропуска",
                    isErrorText = "Номер пропуска неверен!",
                    onTextChange = {pass = it},
                    text = pass
                )
            },
        )
    }
}
