package com.example.trashtrack.ui.feature.employee.splash.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.trashtrack.R

@Composable
fun EmployeeSplashScreen(
    modifier: Modifier = Modifier
) {
    Column {
        Box {
            Icon(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null
            )
        }
    }
}