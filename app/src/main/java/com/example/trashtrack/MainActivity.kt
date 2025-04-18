package com.example.trashtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trashtrack.ui.NavigationHost
import com.example.trashtrack.ui.navigation.NavigationBuilder
import com.example.trashtrack.ui.preferences.SecurePrefsHelper
import com.example.trashtrack.ui.theme.TrashTrackTheme

class MainActivity : ComponentActivity() {

    lateinit var securePrefs: SecurePrefsHelper
    private var navController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        securePrefs = SecurePrefsHelper(this)
        enableEdgeToEdge()
        setContent {

            navController = rememberNavController()

            TrashTrackTheme {
                NavigationHost(
                    navController = navController
                )
            }
        }
    }
}

