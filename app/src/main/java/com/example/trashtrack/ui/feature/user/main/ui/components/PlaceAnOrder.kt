package com.example.trashtrack.ui.feature.user.main.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Composable
fun PlaceAnOrder(
    openPlaceAnOrder: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 39.dp, end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.garbage_collection_from_residential_buildings),
            color = MaterialTheme.colors.black,
            style = TTTypography.headlineLarge
        )
        TTButton(
            onClick = openPlaceAnOrder,
            text = stringResource(R.string.order_button),
        )
    }
}