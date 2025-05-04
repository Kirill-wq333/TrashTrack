package com.example.trashtrack.ui.feature.user.profile.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun PaymentMethodPreview() {
    TTModalBottomSheet(
        onDismissRequest = {}
    ) {
        PaymentMethod()
    }
}

@Composable
fun PaymentMethod(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 7.dp, end = 22.dp, bottom = 53.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Способ оплаты",
            color = MaterialTheme.colors.neutral950,
            style = TTTypography.headlineLarge
        )
        Column {
            IconAndTextItem(
                text = "Банковская карта",
                icon = R.drawable.ic_card_bank
            )
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            IconAndTextItem(
                text = "СБП",
                icon = R.drawable.sbp,
                usePainter = true
            )
        }
    }
}

@Composable
private fun IconAndTextItem(
    modifier: Modifier = Modifier,
    text: String,
    tint: Color = MaterialTheme.colors.black,
    icon: Int,
    usePainter: Boolean = false
) {
    Row(
        modifier = Modifier
            .padding(start = 39.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        if (usePainter) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = tint
            )
        } else {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                tint = tint
            )
        }
        Text(
            text = text,
            color = MaterialTheme.colors.neutral700,
            style = TTTypography.titleLarge
        )
    }
}