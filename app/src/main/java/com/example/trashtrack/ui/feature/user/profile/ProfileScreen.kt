package com.example.trashtrack.ui.feature.user.profile

import android.app.Activity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.trashtrack.R
import com.example.trashtrack.ui.approuts.AppRoutes
import com.example.trashtrack.ui.feature.user.profile.bottomsheet.DeleteAccount
import com.example.trashtrack.ui.feature.user.profile.screens.DataScreen
import com.example.trashtrack.ui.feature.user.profile.screens.SubscriptionScreen
import com.example.trashtrack.ui.shared.bottomsheet.TTModalBottomSheet
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

data class ProfileTextAndIconItem(
    val colorText: Color,
    val colorIcon: Color,
    val text: String,
    val icon: Int,
    val onClick: () -> Unit,

)


@Composable
fun ProfileScreen(
    navController: NavHostController,
    color: Color,
) {
    var currentScreenType by remember { mutableStateOf<ProfileType>(ProfileType.MainProfile) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
    ) {
    when(currentScreenType) {
        ProfileType.MainProfile ->{
            ContentProfile(
                navController = navController,
                openDataScreen = {
                    currentScreenType = ProfileType.DataProfile
                },
                openSubscription = {
                    currentScreenType = ProfileType.SubscriptionProfile
                }
            )
        }
        ProfileType.SubscriptionProfile ->{
            SubscriptionScreen(
                backButton = {
                    currentScreenType = ProfileType.MainProfile
                }
            )
        }
        ProfileType.DataProfile ->{
            DataScreen(
                backButton = {
                    currentScreenType = ProfileType.MainProfile
                }
            )
        }
    }

    }

}

@Composable
fun ContentProfile(
    navController: NavHostController,
    openDataScreen: () -> Unit,
    openSubscription: () -> Unit
) {
    val context = LocalContext.current

    var openDeleteAccount by remember { mutableStateOf(false) }

    val profileItem = listOf(
        ProfileTextAndIconItem(
            colorIcon = MaterialTheme.colors.green600,
            colorText = MaterialTheme.colors.black,
            text = "Мои данные",
            icon = R.drawable.lucide_user_pen,
            onClick = openDataScreen
        ),
        ProfileTextAndIconItem(
            colorIcon = MaterialTheme.colors.green600,
            colorText = MaterialTheme.colors.black,
            text = "Мои подписки",
            icon = R.drawable.mdi_subscriptions,
            onClick = openSubscription
        ),
        ProfileTextAndIconItem(
            colorIcon = MaterialTheme.colors.green600,
            colorText = MaterialTheme.colors.black,
            text = "Выйти из приложения",
            icon = R.drawable.mingcute_exit_line,
            onClick = { (context as Activity).finish() }
        ),
        ProfileTextAndIconItem(
            colorIcon = MaterialTheme.colors.red600,
            colorText = MaterialTheme.colors.red600,
            text = "Удалить аккаунт",
            icon = R.drawable.octicon_trashcan,
            onClick = { openDeleteAccount = true }
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Добрый день",
            color = MaterialTheme.colors.black,
            style = TTTypography.displaySmall,
            modifier = Modifier
                .padding(top = 39.dp, start = 30.dp)
        )
        Spacer(modifier = Modifier.height(58.dp))
        Column(
            modifier = Modifier
                .padding(start = 32.dp),
            verticalArrangement = Arrangement.spacedBy(39.dp)
        ) {
            profileItem.forEach { i ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = i.onClick),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(i.icon),
                        contentDescription = null,
                        tint = i.colorIcon,
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Text(
                        text = i.text,
                        color = i.colorText,
                        style = TTTypography.titleLarge
                    )
                }
            }
        }
    }
    if (openDeleteAccount) {
        TTModalBottomSheet(
            onDismissRequest = { openDeleteAccount = false }
        ) { hide ->
            DeleteAccount(
                openProfileScreen = { hide() },
                openIntroductionScreen = { navController.navigate(AppRoutes.INTRODUCTION); hide() },
            )
        }
    }

}