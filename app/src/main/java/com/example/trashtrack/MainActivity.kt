package com.example.trashtrack

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trashtrack.ui.NavigationHost
//import com.example.trashtrack.ui.preferences.SecurePrefsHelper
import com.example.trashtrack.ui.theme.TrashTrackTheme
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var navController: NavHostController? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, getSharedPreferences("osmdroid", MODE_PRIVATE))
        Configuration.getInstance().userAgentValue = packageName

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

