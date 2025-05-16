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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.mock.DataClasses
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.shared.text.textfield.CommentTextField
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent
import com.example.trashtrack.ui.shared.text.transform.rememberMaskVisualTransformation
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun SubscriptionCompleted(
    color: Color,
    openSubscriptionScreen: () -> Unit,
    subscriptionDetails: DataClasses.SubscriptionDetails
) {
    Content(
        color = color,
        openSubscriptionScreen = openSubscriptionScreen,
        subscriptionDetails = subscriptionDetails
    )
}

@Composable
private fun Content(
    color: Color,
    openSubscriptionScreen: () -> Unit,
    subscriptionDetails: DataClasses.SubscriptionDetails,
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
                visualTransformation = rememberMaskVisualTransformation("##.##.####"),
                text = subscriptionDetails.date,
                nameTextField = stringResource(R.string.date_subscription),
                padding = 22.dp,
                isErrorText = "",
                readOnly = true,
                onTextChange = {},
            )
            Spacer(modifier = Modifier.height(15.dp))
            EjectionTimeRead(time = subscriptionDetails.time)
            Spacer(modifier = Modifier.height(26.dp))
            CommentTextField(
                readOnly = true,
                startPadding = 19.dp,
                endPadding = 29.dp,
                comment = subscriptionDetails.comment,
            )
            Spacer(modifier = Modifier.height(26.dp))
            Column(
                modifier = Modifier
                    .padding(start = 17.dp)
            ) {
                Text(
                    text = stringResource(R.string.courier_picks_up_trash),
                    color = MaterialTheme.colors.neutral400,
                    style = TTTypography.titleLarge,
                    modifier = Modifier
                        .padding(start = 3.dp)
                )
                Spacer(modifier = Modifier.height(17.dp))
                Text(
                    text = stringResource(R.string.everyday),
                    color = MaterialTheme.colors.black,
                    style = TTTypography.titleMedium
                )
            }
        }
        TTButton(
            text = stringResource(R.string.my_subscription),
            onClick = openSubscriptionScreen,
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
            text = stringResource(R.string.subscription_completed),
            color = MaterialTheme.colors.black,
            style = TTTypography.headlineLarge,
            textAlign = TextAlign.Start
        )

    }
}

@Composable
fun EjectionTimeRead(
    time: String
) {
    Column(
        modifier = Modifier
            .padding(start = 27.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.ejection_time),
            color = MaterialTheme.colors.neutral400,
            style = TTTypography.titleLarge
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = time,
            color = MaterialTheme.colors.black,
            style = TTTypography.titleMedium
        )
    }
}