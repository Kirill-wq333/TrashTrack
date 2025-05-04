package com.example.trashtrack.ui.feature.user.profile.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
//import com.example.trashtrack.ui.preferences.AuthManager
import com.example.trashtrack.ui.shared.button.TTBottom
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun DeleteAccountPreview() {
    TTModalBottomSheet(
        onDismissRequest = {}
    ) {
        DeleteAccount(
            openProfileScreen = {},
            openIntroductionScreen = {}
        )
    }
}


@Composable
fun DeleteAccount(
    modifier: Modifier = Modifier,
    openProfileScreen: () -> Unit,
    openIntroductionScreen: () -> Unit
) {
    val context = LocalContext.current

//    val authManager = remember { AuthManager.getInstance(context) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 22.dp, end = 22.dp, bottom = 37.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(21.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Удаление аккаунта",
                color = MaterialTheme.colors.neutral950,
                style = TTTypography.headlineLarge
            )

            Text(
                text = "Вы уверены, что хотите удалить аккаунт?",
                color = MaterialTheme.colors.neutral500,
                style = TTTypography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(49.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(11.dp)
        ) {
            TTBottom(
                text = "Да",
                onClick = {
//                    authManager.deleteAccount();
                    openIntroductionScreen()
                          },
            )
            TTBottom(
                text = "Отмена",
                onClick = openProfileScreen,
                color = MaterialTheme.colors.neutral400,
            )
        }
    }

}