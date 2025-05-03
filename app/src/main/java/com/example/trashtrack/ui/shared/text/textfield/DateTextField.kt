package com.example.trashtrack.ui.shared.text.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import com.example.trashtrack.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.shared.picker.date.DatePicker
import com.example.trashtrack.ui.shared.text.transform.rememberMaskVisualTransformation
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@Composable
fun DateTextField(
    modifier: Modifier = Modifier,
    date: String,
    dateFormat: String = "dd.MM.yyyy",
    onDateChange: (String) -> Unit,
    readOnly: Boolean = false
) {
    var isError by remember { mutableStateOf(false) }
    var openDatePicker by remember { mutableStateOf(false) }

    val dateTimestampMillis: Long = remember(date) {
        runCatching {
            DateTimeFormatter.ofPattern(dateFormat)
                .parse(date)
                .query(LocalDate::from)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()
        }.getOrDefault(System.currentTimeMillis())
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = date,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colors.green600,
                unfocusedContainerColor = MaterialTheme.colors.white,
                focusedTextColor = MaterialTheme.colors.black,
                unfocusedTextColor = MaterialTheme.colors.black,
                focusedContainerColor = MaterialTheme.colors.white,
                errorContainerColor = MaterialTheme.colors.white,
                errorIndicatorColor = MaterialTheme.colors.red600,
                cursorColor = MaterialTheme.colors.green600
            ),
            onValueChange = { newDate ->
                val digitsOnly = newDate.filter { it.isDigit() }.take(8)
                onDateChange(digitsOnly)
            },
            visualTransformation = rememberMaskVisualTransformation("##.##.####"),
            label = {
                Text(
                    text = "Дата начала подписки",
                    color = MaterialTheme.colors.neutral400,
                    style = TTTypography.titleLarge,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_calendar),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable{
                            openDatePicker = true
                        }
                )
            },
            isError = isError,
            readOnly = readOnly,
            modifier = Modifier.fillMaxWidth()
        )

        if (isError) {
            Text(
                text = "Укажите дату",
                color = MaterialTheme.colors.red600,
                style = TTTypography.titleLarge,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
    if (openDatePicker) {
        TTModalBottomSheet(onDismissRequest = { openDatePicker = false }) { hide ->
            Box(
                modifier = Modifier.padding(
                    top = 15.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 24.dp
                ),
                contentAlignment = Alignment.Center
            ) {
                DatePicker(
                    dateToSet = dateTimestampMillis,
                    onConfirmDate = { millis ->
                        val instant = Instant.ofEpochMilli(millis)
                        val zoneId = ZoneId.systemDefault()
                        val localDate = instant.atZone(zoneId).toLocalDate()

                        val formatted = DateTimeFormatter
                            .ofPattern(dateFormat)
                            .format(localDate)

                        onDateChange(formatted)
                        hide()
                    },
                    onHide = { hide() }
                )
            }
        }
    }
}