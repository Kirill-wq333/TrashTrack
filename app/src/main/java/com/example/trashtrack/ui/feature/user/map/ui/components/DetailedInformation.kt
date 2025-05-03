package com.example.trashtrack.ui.feature.user.map.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.button.TTBottom
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors
import kotlinx.coroutines.delay

@Preview(device = "spec:width=360dp,height=640dp")
@Preview(device = "spec:width=480dp,height=800dp")
@Preview(device = "spec:width=1440dp,height=2560dp")
@Preview
@Composable
private fun DetailedInformationPreview() {
    Content(
        color = MaterialTheme.colors.white
    )
}

@Composable
private fun Content(
    color: Color
) {
    var address by remember { mutableStateOf("") }
    var numberRoom by remember { mutableStateOf("") }
    var floor by remember { mutableStateOf("") }
    var entrance by remember { mutableStateOf("") }
    var intercomSystem by remember { mutableStateOf("") }
    var isEnabled by remember { mutableStateOf(false) }

    DetailedInformationContent(
        color = color,
        address = address,
        numberRoom = numberRoom,
        floor = floor,
        entrance = entrance,
        intercomSystem = intercomSystem,
        onAddressChange = { address =it },
        onNumberRoomChange = { numberRoom = it },
        onFloorChange = { floor =it },
        onEntranceChange = { entrance =it },
        onIntercomSystemChange = { intercomSystem = it },
        onClickSwitch = { isEnabled = !isEnabled },
        backButton = {}
    )
}

@Composable
fun DetailedInformationContent(
    color: Color,
    address: String,
    numberRoom: String,
    floor: String,
    entrance: String,
    intercomSystem: String,
    onNumberRoomChange: (String) -> Unit,
    onFloorChange: (String) -> Unit,
    onEntranceChange: (String) -> Unit,
    onIntercomSystemChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    onClickSwitch: () -> Unit,
    backButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        HeadingAndBackButton(
            backButton = backButton
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextFieldComponent(
                text = address,
                padding = 0.dp,
                nameTextField = "Введите адрес",
                isErrorText = "Поле не заполнено",
                onTextChange = onAddressChange,
            )
            Spacer(modifier = Modifier.height(24.dp))
            ColumnOutlinedTextField(
                numberRoom = numberRoom,
                floor = floor,
                entrance = entrance,
                intercomSystem = intercomSystem,
                onNumberRoomChange = onNumberRoomChange,
                onFloorChange = onFloorChange,
                onEntranceChange = onEntranceChange,
                onIntercomSystemChange = onIntercomSystemChange

            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Switchers(onClickSwitch = onClickSwitch)
        Spacer(modifier = Modifier.height(44.dp))
        TTBottom(
            onClick = {},
            paddingHorizontal = 26.dp,
            text = "Оформить подписку"
        )
    }
}

@Composable
private fun HeadingAndBackButton(backButton: () -> Unit) {
    BackButton(
        paddingStart = 13.dp,
        backButton = backButton,
        paddingTop = 11.dp,
        paddingIcon = 12.dp,
        sizeIcon = 45.dp
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Введите подробные данные",
        color = MaterialTheme.colors.black,
        style = TTTypography.headlineLarge,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .padding(start = 10.dp)
    )
}

@Composable
private fun ColumnOutlinedTextField(
    numberRoom: String,
    floor: String,
    entrance: String,
    intercomSystem: String,
    onNumberRoomChange: (String) -> Unit,
    onFloorChange: (String) -> Unit,
    onEntranceChange: (String) -> Unit,
    onIntercomSystemChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            OutlinedTextFieldComponent(
                text = numberRoom,
                padding = 0.dp,
                nameTextField = "Номер квартиры*",
                isErrorText = "Поле не заполнено",
                onTextChange = onNumberRoomChange,
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(5.dp))
            OutlinedTextFieldComponent(
                text = floor,
                padding = 0.dp,
                nameTextField = "Этаж*",
                isErrorText = "Поле не заполнено",
                onTextChange = onFloorChange,
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextFieldComponent(
                text = entrance,
                padding = 0.dp,
                nameTextField = "Подьезд*",
                isErrorText = "Поле не заполнено",
                onTextChange = onEntranceChange,
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(5.dp))
            OutlinedTextFieldComponent(
                text = intercomSystem,
                padding = 0.dp,
                nameTextField = "Домофон",
                isErrorText = "Поле не заполнено",
                onTextChange = onIntercomSystemChange,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun SwitchPreview() {
    var isEnabled by remember { mutableStateOf(false) }
    Surface() {
        CustomSwitch(
            onClickSwitch = { isEnabled = !isEnabled }
        )
    }
}

@Composable
fun CustomSwitch(
    onClickSwitch: () -> Unit
) {
    var switchState by remember { mutableStateOf(SwitchState.ACTIVE) }
    var alignment by remember { mutableStateOf(Alignment.CenterStart) }

    val onClick = {
        switchState = when(switchState) {
            SwitchState.ACTIVE -> SwitchState.NOT_ACTIVE
            SwitchState.NOT_ACTIVE -> SwitchState.ACTIVE
        }
        onClickSwitch()
    }

    LaunchedEffect(switchState) {
        when(switchState) {
            SwitchState.ACTIVE -> {
                delay(400)
                alignment = Alignment.CenterStart
            }
            SwitchState.NOT_ACTIVE -> {
                delay(400)
                alignment = Alignment.CenterEnd
            }
        }
    }

    var colorBorder = animateColorAsState(
        targetValue = when (switchState) {
            SwitchState.ACTIVE -> MaterialTheme.colors.neutral400
            SwitchState.NOT_ACTIVE -> MaterialTheme.colors.green600
        },
        animationSpec = tween(durationMillis = 1500)
    )

    var backgroundColor = animateColorAsState(
        targetValue = when (switchState) {
            SwitchState.ACTIVE -> MaterialTheme.colors.white
            SwitchState.NOT_ACTIVE -> MaterialTheme.colors.green600
        },
        animationSpec = tween(durationMillis = 1500)
    )

    var circleColor = animateColorAsState(
        targetValue = when (switchState) {
            SwitchState.ACTIVE -> MaterialTheme.colors.neutral400
            SwitchState.NOT_ACTIVE -> MaterialTheme.colors.neutral50
        },
        animationSpec = tween(durationMillis = 1500)
    )

    Box(
        modifier = Modifier
            .clickable(
                onClick = onClick,
            )
            .width(40.dp)
            .background(color = backgroundColor.value, shape = RoundedCornerShape(12.dp))
            .border(width = 2.dp, color = colorBorder.value, shape = RoundedCornerShape(12.dp)),
        contentAlignment = alignment
    ){
        Box(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 3.dp)
                .size(14.dp)
                .background(color = circleColor.value, shape = CircleShape)
        )
    }
}

@Composable
fun Switchers(
    onClickSwitch: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = "Не звонить",
            color = MaterialTheme.colors.black,
            style = TTTypography.titleLarge
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Курьер позвонит только в случае крайней необходимости",
                color = MaterialTheme.colors.neutral400,
                style = TTTypography.bodyMedium,
                modifier = Modifier
                    .weight(0.5f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            CustomSwitch(
                onClickSwitch = onClickSwitch
            )
        }
        Spacer(modifier = Modifier.height(39.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Заполнить адрес",
                color = MaterialTheme.colors.black,
                style = TTTypography.titleLarge
            )
            CustomSwitch(
                onClickSwitch = onClickSwitch
            )
        }
    }
}

enum class SwitchState {
    NOT_ACTIVE,
    ACTIVE
}