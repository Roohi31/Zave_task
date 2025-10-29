package me.sabapro.zave_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.auth
import me.sabapro.zave_task.screen.AuthenticationScreen
import me.sabapro.zave_task.screen.HomeScreen
import me.sabapro.zave_task.screen.SearchResultScreen
import me.sabapro.zave_task.screen.SettingScreen
import me.sabapro.zave_task.ui.theme.Zave_TaskTheme

const val webClientId = "1036294739265-gh385b4c8m12b5tleui6o4mibn6fnlv1.apps.googleusercontent.com"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            Zave_TaskTheme {
                AuthApp()
            }
        }
    }
}

@Composable
fun AuthApp(){
    val navController = rememberNavController()
    val currentUser = Firebase.auth.currentUser
    NavHost(
        navController = navController,
        startDestination = if (currentUser != null) "home" else "authentication")
    {
        composable("authentication") { AuthenticationScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("searchResult") { SearchResultScreen(navController) }
        composable("setting") { SettingScreen(navController) }
    }
}