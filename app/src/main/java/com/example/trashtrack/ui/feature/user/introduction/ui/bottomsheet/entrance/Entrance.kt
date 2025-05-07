package com.example.trashtrack.ui.feature.user.introduction.ui.bottomsheet.entrance

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.feature.user.introduction.ui.bottomsheet.registration.Services
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent
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
            openScreen = {},
            content = {},
            visibleBoxs = true
        )
    }
}

@Composable
fun EntranceContent(
    openEntranceScreen: () -> Unit = {},
    openRegistrationScreen: () -> Unit,
    openScreen: () -> Unit,
    content: @Composable () -> Unit = {},
    visibleBoxs: Boolean,
    visibleContent: Boolean = false
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
                nameTextField = stringResource(R.string.email_text_field_label),
                isErrorText = stringResource(R.string.error_email),
                text = email,
                onTextChange = { email = it }
            )
            Spacer(modifier = Modifier.height(25.dp))
            OutlinedTextFieldComponent(
                nameTextField = stringResource(R.string.entering_a_password),
                isErrorText = stringResource(R.string.error_entering_a_password),
                text = password,
                onTextChange = { password = it }
            )
            if(visibleContent) {
                Spacer(modifier = Modifier.height(25.dp))
                content()
            }
        }
        Spacer(modifier = Modifier.height(37.dp))
        TTButtons(
            openMainScreen = openScreen,
        )
        Spacer(modifier = Modifier.height(30.2.dp))
        Row {
            Text(
                text = stringResource(R.string.you_have_not_account),
                color = Color.Black,
                style = TTTypography.titleLarge
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(R.string.register_introduction),
                color = Color.Black,
                textDecoration = TextDecoration.Underline,
                style = TTTypography.titleLarge,
                modifier = Modifier
                    .clickable(onClick = openRegistrationScreen)
            )
        }
        Spacer(modifier = Modifier.height(12.5.dp))
        Services()
        Spacer(modifier = Modifier.height(11.dp))
        if (visibleBoxs) {
            Boxs(
                openEntranceScreen = openEntranceScreen
            )
        }
    }
}

@Composable
private fun TTButtons(
    openMainScreen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TTButton(
            onClick = openMainScreen,
            text = stringResource(R.string.entrance_button),
            color = MaterialTheme.colors.green600
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacers.small))
        Text(
            text = stringResource(R.string.forgot_your_password),
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
                text = stringResource(R.string.entrance_button),
                textDecoration = TextDecoration.Underline,
                color = Color.Black,
                style = TTTypography.titleLarge,
                modifier = Modifier
                    .clickable(onClick = openEntranceScreen)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = stringResource(R.string.employee_introduction_title),
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
            text = stringResource(R.string.entrance_in_system),
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