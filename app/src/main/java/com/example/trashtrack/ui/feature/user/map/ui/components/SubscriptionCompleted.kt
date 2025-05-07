package com.example.trashtrack.ui.feature.user.map.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.shared.text.textfield.CommentTextField
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun SubscriptionCompletedPreview() {
        SubscriptionCompleted(
            color = MaterialTheme.colors.white
        )
}

@Composable
fun SubscriptionCompleted(
    color: Color
) {
    Content(
        color = color
    )
}

@Composable
private fun Content(
    color: Color
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = color),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Header()
            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextFieldComponent(
                text = "12.11.2042",
                nameTextField = "Дата начала подписки",
                padding = 22.dp,
                isErrorText = "",
                readOnly = true,
                onTextChange = {},
            )
            Spacer(modifier = Modifier.height(15.dp))
            EjectionTimeRead()
            Spacer(modifier = Modifier.height(26.dp))
            CommentTextField(
                startPadding = 19.dp,
                endPadding = 29.dp
            )
            Spacer(modifier = Modifier.height(26.dp))
            Column(
                modifier = Modifier
                    .padding(start = 17.dp)
            ) {
                Text(
                    text = "Курьер будет забирать мусор",
                    color = MaterialTheme.colors.neutral400,
                    style = TTTypography.titleLarge,
                    modifier = Modifier
                        .padding(start = 3.dp)
                )
                Spacer(modifier = Modifier.height(17.dp))
                Text(
                    text = "Каждый день",
                    color = MaterialTheme.colors.black,
                    style = TTTypography.titleMedium
                )
            }
        }
        TTButton(
            text = "Мои подписки",
            onClick = {},
            modifier = Modifier
                .padding(
                    start = 23.dp,
                    end = 25.dp,
                    bottom = 30.dp
                )
        )
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 26.dp,
                top = 25.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.green200,
                    shape = RoundedCornerShape(6.dp)
                )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_gridicons_checkmark),
                contentDescription = null,
                tint = MaterialTheme.colors.green600,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        top = 3.dp,
                        end = 8.dp,
                        bottom = 11.dp
                    )
                    .size(width = 54.dp, height = 55.dp)
            )
        }

        Spacer(modifier = Modifier.width(21.dp))

        Text(
            text = "Подписка оформлена",
            color = MaterialTheme.colors.black,
            style = TTTypography.headlineLarge,
            textAlign = TextAlign.Start
        )

    }
}

@Composable
fun EjectionTimeRead() {
    Column(
        modifier = Modifier
            .padding(start = 27.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Желаемое воемя выноса",
            color = MaterialTheme.colors.neutral400,
            style = TTTypography.titleLarge
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "12:00",
            color = MaterialTheme.colors.black,
            style = TTTypography.titleMedium
        )
    }
}