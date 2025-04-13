package com.example.trashtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.trashtrack.ui.feature.presintation.navigation.NavigationBuilder
import com.example.trashtrack.ui.feature.presintation.splash.ui.DualAxisAnimationScreen
import com.example.trashtrack.ui.theme.TrashTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            TrashTrackTheme {
                NavigationBuilder(
                    navController = navController
                )
            }
        }
    }
}

