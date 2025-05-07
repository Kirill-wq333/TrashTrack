package com.example.trashtrack.ui.navigation.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.trashtrack.R
import com.example.trashtrack.ui.approuts.AppRoutes
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

data class BottomBarItems(
    val route: String,
    val icon: ImageVector, // Or Int if using resource IDs
    val label: String,
)



@Composable
fun BottomBarUser(
    navController: NavHostController
) {
    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    val bottomBarItem = listOf(
        BottomBarItems(
            route = AppRoutes.USER,
            label = stringResource(R.string.main_bottom_bar),
            icon = Icons.Default.Home
        ),
        BottomBarItems(
            route = AppRoutes.ORDERS,
            label = stringResource(R.string.order_bottom_bar),
            icon = Icons.AutoMirrored.Filled.List
        ),
        BottomBarItems(
            route = AppRoutes.PROFILE_USER,
            label = stringResource(R.string.profile_bottom_bar),
            icon = Icons.Default.AccountCircle
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.neutral100),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(60.dp)
        ) {
            bottomBarItem.forEach { item ->
                val isSelected = currentRoute == item.route

                val color = if (isSelected) MaterialTheme.colors.green600
                else MaterialTheme.colors.primary600
                BottomBarItem(
                    imageVector = item.icon,
                    text = item.label,
                    color = color,
                    onClick = { navController.navigate(item.route) },
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    imageVector: ImageVector,
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = color,
        )
        Text(
            text = text,
            color = color,
            style = TTTypography.titleLarge
        )
    }
}