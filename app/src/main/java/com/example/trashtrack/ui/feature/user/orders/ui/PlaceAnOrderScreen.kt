package com.example.trashtrack.ui.feature.user.orders.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.mock.DataClasses
import com.example.trashtrack.ui.feature.user.orders.ui.components.Subscription
import com.example.trashtrack.ui.shared.button.back.BackButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors


@Preview
@Composable
private fun OrdersPreview() {
    Surface {
        PlaceAnOrderScreen(
            backButton = {},
            onSubscriptionClick = {},
            subscription = listOf(),
        )
    }
}

@Composable
fun PlaceAnOrderScreen(
    subscription: List<DataClasses.SubscriptionData>,
    backButton: () -> Unit,
    onSubscriptionClick: (Int) -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        BackButton(
            backButton = backButton,
            paddingTop = 17.dp,
            color = MaterialTheme.colors.neutral300,
            paddingStart = 16.dp
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = stringResource(R.string.place_order),
            color = MaterialTheme.colors.black,
            style = TTTypography.displaySmall,
            modifier = Modifier.padding(start = 25.dp)
        )
        Spacer(modifier = Modifier.height(9.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 9.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            subscription.forEachIndexed { index, subscriptionData ->
                Subscription(
                    benefit = subscriptionData.benefit,
                    backgroundColor = subscriptionData.background,
                    borderColor = subscriptionData.border,
                    colorText = subscriptionData.text,
                    heading = subscriptionData.heading,
                    underHeading = subscriptionData.underHeading,
                    money = subscriptionData.money,
                    price = subscriptionData.price,
                    visibleMoneyAndPrice = subscriptionData.visible,
                    openMapScreen = { onSubscriptionClick(index) }
                )
            }
        }
    }
}