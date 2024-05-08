package com.techuntried.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.techuntried.notes.navigation.Screens
import com.techuntried.notes.screens.home.HomeScreen
import com.techuntried.notes.screens.note.NoteScreen
import com.techuntried.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                val navController = rememberNavController()
                App(navController = navController)
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Toolbar(navController: NavHostController) {
//    val destination: NavDestination? =
//        navController.currentBackStackEntryAsState().value?.destination
//    val route = destination?.route
//    val title = when (route) {
//        Screens.HomeScreen.route -> "Home"
//        Screens.NoteScreen.route -> "Add note"
//        else -> ""
//    }
//    val context = LocalContext.current
//    TopAppBar(
//        title = { Text(text = title) },
//        actions = {
//            if (route == Screens.NoteScreen.route) {
//                IconButton(
//                    onClick = {})
//                {
//                    Icon(Icons.Default.Delete, "")
//                }
//            }
//
//        }
//    )
//}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                onAddClick =
                {
                    navController.navigate(Screens.NoteScreen.passId(-1L))
                },
                onNoteClick =
                {
                    navController.navigate(Screens.NoteScreen.passId(it))
                }
            )
        }

        composable(Screens.NoteScreen.route, arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
                defaultValue = -1L
            }
        )) {
            NoteScreen { navController.popBackStack() }
        }
    }
}

