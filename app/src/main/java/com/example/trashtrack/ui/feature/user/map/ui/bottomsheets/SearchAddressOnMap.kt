package com.example.trashtrack.ui.feature.user.map.ui.bottomsheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.shared.button.TTButton
import com.example.trashtrack.ui.shared.text.textfield.OutlinedTextFieldComponent
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun SearchAddressOnMapPreview() {
    SearchAddressOnMap { }
}


@Composable
fun SearchAddressOnMap(
    modifier: Modifier = Modifier,
    nextOpenData: () -> Unit
) {
    var address by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 17.dp, end = 17.dp, bottom = 47.dp, top = 27.dp)
    ) {
        Text(
            text = stringResource(R.string.address_trash),
            color = MaterialTheme.colors.neutral500,
            style = TTTypography.titleLarge
        )
        Spacer(modifier = Modifier.height(28.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            OutlinedTextFieldComponent(
                text = address,
                padding = 10.dp,
                nameTextField = stringResource(R.string.address),
                isErrorText = stringResource(R.string.error_address),
                onTextChange = { address = it },
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_address),
                        color = MaterialTheme.colors.neutral500,
                        style = TTTypography.titleLarge
                    )
                }
            )
            Spacer(modifier = Modifier.height(19.dp))
            TTButton(
                modifier = Modifier.padding(start = 10.dp),
                text = stringResource(R.string.address_completed_button),
                onClick = nextOpenData,
            )
        }
    }

}