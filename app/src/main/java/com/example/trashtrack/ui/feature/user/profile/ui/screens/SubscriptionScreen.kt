package com.example.trashtrack.ui.feature.user.profile.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trashtrack.mock.DataClasses
import com.example.trashtrack.ui.feature.user.orders.ui.components.Subscription
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun SubscriptionScreen(
    backButton: () -> Unit,
    subscription: DataClasses.SubscriptionData? = null
) {

        Subscriptions(
            backButton = backButton,
            subscription = subscription
        )
}

@Composable
private fun Subscriptions(
    backButton: () -> Unit,
    subscription: DataClasses.SubscriptionData? = null
) {

    Column(
        modifier = Modifier
            .padding(
                start = 17.dp,
                top = 16.dp
            )
    ) {
        BackButton(
            backButton = backButton,
            paddingStart = 0.dp,
            color = MaterialTheme.colors.neutral300
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = "Мои подписки",
            color = MaterialTheme.colors.black,
            style = TTTypography.displaySmall,
            modifier = Modifier
                .padding(start = 9.dp)
        )
        Spacer(modifier = Modifier.height(17.dp))
        SubscriptionContent(
            subscription = subscription
        )
    }
}

@Composable
fun SubscriptionContent(
    subscription: DataClasses.SubscriptionData? = null
) {
    subscription?.let {
        Subscription(
            backgroundColor = MaterialTheme.colors.red600,
            borderColor = MaterialTheme.colors.green500,
            price = it.price,
            benefit = it.benefit,
            heading = it.heading,
            underHeading = it.underHeading,
            money = it.money,
            visibleMoneyAndPrice = it.visible,
            openMapScreen = {},
        )
    }
}