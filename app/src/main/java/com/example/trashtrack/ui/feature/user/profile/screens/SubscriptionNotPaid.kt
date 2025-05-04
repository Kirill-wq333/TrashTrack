package com.example.trashtrack.ui.feature.user.profile.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.feature.user.map.ui.components.EjectionTimeRead
import com.example.trashtrack.ui.feature.user.profile.bottomsheet.DeleteSubscriptionBS
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.shared.button.TTBottom
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.shared.text.textfield.CommentTextField
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun SubscriptionNotPaidPreview() {
    SubscriptionNotPaid(
        color = MaterialTheme.colors.white
    )
}

@Composable
fun SubscriptionNotPaid(
    modifier: Modifier = Modifier,
    color: Color
) {
    var openDeleteBS by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = color),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Content(
            openDeleteBS = { openDeleteBS = true }
        )
        TTBottom(
            text = "Оплатить подписку",
            onClick = {},
            modifier = Modifier
                .padding(start = 23.dp, end = 25.dp, bottom = 30.dp)
        )
    }
    if (openDeleteBS){
        TTModalBottomSheet(
            onDismissRequest = { openDeleteBS = false }
        ) {hide ->
            DeleteSubscriptionBS(backClick = { hide() })
        }
    }
}

@Composable
private fun Content(
    openDeleteBS: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BackButton(
            paddingStart = 23.dp,
            paddingTop = 23.dp,
            backButton = {},
        )
        Spacer(modifier = Modifier.height(15.dp))
        HeadingAndStatusNotPaid()
        Spacer(modifier = Modifier.height(21.dp))
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
        CommentTextField()
        Spacer(modifier = Modifier.height(26.dp))
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = "Курьер будет забирать мусор",
                color = MaterialTheme.colors.neutral400,
                style = TTTypography.titleLarge,
                modifier = Modifier
                    .padding(start = 3.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Каждый день",
                color = MaterialTheme.colors.black,
                style = TTTypography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(23.dp))
        DeleteSubscription(
            openDeleteBS = openDeleteBS
        )
    }
}

@Composable
fun HeadingAndStatusNotPaid() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 25.dp)
    ){
        Text(
            text = "Подписка  на разовый вынос мусора",
            color = MaterialTheme.colors.black,
            style = TTTypography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colors.red600,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "НЕ ОПЛАЧЕНО",
                color = MaterialTheme.colors.white,
                style = TTTypography.headlineLarge,
                modifier = Modifier.padding(vertical = 1.dp)
            )
        }
    }
}

@Composable
private fun DeleteSubscription(
    openDeleteBS: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 13.dp)
            .clickable(onClick = openDeleteBS),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.octicon_trashcan),
            contentDescription = null,
            tint = MaterialTheme.colors.red600,
            modifier = Modifier
                .size(16.dp)
        )
        Text(
            text = "Удалить подписку",
            color = MaterialTheme.colors.red600,
            style = TTTypography.titleLarge
        )
    }
}