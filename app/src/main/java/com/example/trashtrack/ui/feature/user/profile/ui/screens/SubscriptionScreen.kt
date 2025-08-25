package com.example.trashtrack.ui.feature.user.profile.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    subId: Int?,
    subscription: List<DataClasses.SubscriptionData>
) {
    Subscriptions(
        backButton = backButton,
        subscription = subscription,
        subId = subId
    )
}

@Composable
private fun Subscriptions(
    backButton: () -> Unit,
    subscription: List<DataClasses.SubscriptionData>,
    subId: Int?
) {

    val subscriptionId = remember(subId) {
        subscription.firstOrNull { it.id == subId }
    }

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

        subscriptionId?.let {
            SubscriptionContent(
                subscription = it,
                openSubscriptionNotPaid = {}
            )
        }
    }

}

@Composable
fun SubscriptionContent(
    subscription: DataClasses.SubscriptionData,
    openSubscriptionNotPaid: () -> Unit
) {
    val validLength = setOf(17, 21)

    Subscription(
        backgroundColor = if (subscription.heading.length in validLength) MaterialTheme.colors.white else MaterialTheme.colors.red600,
        borderColor = if (subscription.heading.length in validLength) MaterialTheme.colors.red600 else MaterialTheme.colors.green500,
        price = subscription.price,
        colorHeading = if (subscription.heading.length in validLength) MaterialTheme.colors.neutral950 else MaterialTheme.colors.white,
        colorUnHeading = if (subscription.heading.length in validLength) MaterialTheme.colors.neutral950 else MaterialTheme.colors.white,
        benefit = subscription.benefit,
        heading = subscription.heading,
        underHeading = subscription.underHeading,
        money = subscription.money,
        visibleMoneyAndPrice = subscription.visible,
        openMapScreen = openSubscriptionNotPaid,
    )
}