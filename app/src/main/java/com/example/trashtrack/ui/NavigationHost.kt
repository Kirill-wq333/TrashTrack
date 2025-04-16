package com.example.trashtrack.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.trashtrack.ui.navigation.NavigationBuilder
import com.example.trashtrack.ui.navigation.bottombar.BottomBarUser

@Composable
fun NavigationHost(
    navController: NavHostController?
) {
    if (navController == null) return
    var visibleBottomBarUser by remember { mutableStateOf(false) }
    var visibleBottomBarEmployee by remember { mutableStateOf(false) }

    HostScaffold(
        bottomBar = {
            if (visibleBottomBarUser) {
                BottomBarUser(
                    navController = navController
                )
            }
        }
    ) {paddingValues ->
        NavigationBuilder(
            navController = navController,
            paddingValues = paddingValues,
            setVisibleBottomBarUser = { visible -> visibleBottomBarUser = visible },
            setVisibleBottomBarEmployee = { visible -> visibleBottomBarEmployee = visible }
        )
    }
}

@Composable
private fun HostScaffold(
    bottomBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = bottomBar,
        content = content,
    )

}