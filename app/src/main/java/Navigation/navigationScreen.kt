package Navigation

import SplashScreen.OpenScreen
import UI.HomeScreen
import UI.SearchScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            OpenScreen(navController)
        }

        composable("home") {
            HomeScreen( navController = navController)
        }

        composable(
            route = "surah/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val surahName = backStackEntry.arguments?.getString("name") ?: ""
            HomeScreen( navController = navController )
        }


        composable("search") {
            SearchScreen(navController)
        }
    }
}
