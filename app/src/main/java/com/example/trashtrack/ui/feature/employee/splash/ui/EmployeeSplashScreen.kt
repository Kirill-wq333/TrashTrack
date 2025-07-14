package com.example.trashtrack.ui.feature.employee.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashtrack.R
import com.example.trashtrack.ui.theme.TTTypography
import com.example.trashtrack.ui.theme.colors

@Preview
@Composable
private fun EmployeeSplashScreenPreview() {
    EmployeeSplashScreen()
}

@Composable
fun EmployeeSplashScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.white),
        contentAlignment = Alignment.Center
    ){
        EmployeeSplashContent()
    }
}

@Composable
private fun EmployeeSplashContent(
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Icon(
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(188.dp, 173.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(R.string.splash_employee),
                color = MaterialTheme.colors.black,
                style = TTTypography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(166.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.download_server),
                color = MaterialTheme.colors.neutral700,
                style = TTTypography.bodyLarge
            )
            Spacer(modifier = Modifier.height(30.dp))
            CircularProgressIndicator(
                color = MaterialTheme.colors.black,
                modifier = Modifier
                    .size(36.dp)
            )
        }
    }

}