package com.example.trashtrack.ui.feature.user.profile.ui.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun PaymentFunctionNotValidPreview() {
    TTModalBottomSheet(
        onDismissRequest = {}
    ) {
        PaymentFunctionNotValid()
    }
}


@Composable
fun PaymentFunctionNotValid(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 23.dp, end = 23.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Функция оплаты не действительная",
            color = MaterialTheme.colors.neutral950,
            style = TTTypography.headlineLarge
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = "Попробуйте чуть позже",
            color = MaterialTheme.colors.neutral700,
            style = TTTypography.titleLarge
        )
        Spacer(modifier = Modifier.height(21.dp))
        TTButton(
            text = "ОК",
            onClick = {}
        )
    }
}