package com.example.trashtrack.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.trashtrack.ui.navigation.NavigationBuilder

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController?
) {
    if (navController == null) return

    HostScaffold(
        bottomBar = {}
    ) {
        NavigationBuilder(
            navController = navController
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