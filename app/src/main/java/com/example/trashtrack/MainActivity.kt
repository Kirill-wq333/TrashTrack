package com.example.trashtrack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trashtrack.ui.NavigationHost
//import com.example.trashtrack.ui.preferences.SecurePrefsHelper
import com.example.trashtrack.ui.theme.TrashTrackTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

//    @Inject lateinit var securePrefs: SecurePrefsHelper
    private var navController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        securePrefs = SecurePrefsHelper(applicationContext)
//
//        if (!securePrefs.validateDataIntegrity()) {
//            Toast.makeText(this, "Ошибка безопасности. Данные будут сброшены.", Toast.LENGTH_LONG).show()
//            securePrefs.resetAllSecurityData()
//        }
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

