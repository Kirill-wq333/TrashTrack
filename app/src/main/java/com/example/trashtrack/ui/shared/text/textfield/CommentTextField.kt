package com.example.trashtrack.ui.shared.text.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun CommentTextField(
    startPadding: Dp = 25.dp,
    endPadding: Dp = 23.dp
) {
    var comment by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = comment,
        onValueChange = { comment = it },
        label = { Text(
            text = "Комментарий курьеру",
            color = MaterialTheme.colors.neutral400,
            style = TTTypography.titleLarge,
        ) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = startPadding, end = endPadding)
            .heightIn(min = 56.dp, max = 180.dp),
        singleLine = false,
        maxLines = 5,
        textStyle = LocalTextStyle.current.copy(
            lineHeight = 20.sp
        ),
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
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}