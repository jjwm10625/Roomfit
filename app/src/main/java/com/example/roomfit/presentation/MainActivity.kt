// app/src/main/java/com/example/roomfit/presentation/MainActivity.kt
package com.example.roomfit.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomfit.ScrapViewModel
import com.example.roomfit.presentation.detail.DetailScreen
import com.example.roomfit.presentation.detail.DetailScreen2
import com.example.roomfit.presentation.detail.DetailScreen4
import com.example.roomfit.presentation.detail.DetailScreen5
import com.example.roomfit.presentation.login.FindPwScreen
import com.example.roomfit.presentation.login.LoginScreen
import com.example.roomfit.presentation.login.PwResultScreen
import com.example.roomfit.presentation.login.SignUpScreen
import com.example.roomfit.presentation.login.UserInfoScreen
import com.gdg.kakaobank.presentation.navigator.RoomNav
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.RoomfitTheme
import com.example.roomfit.util.PreferencesManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val scrapViewModel: ScrapViewModel by viewModels()

        setContent {
            RoomfitTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val prefs = remember { PreferencesManager(context) }
                val startDestination = if (prefs.isLoggedIn) "home" else "login"
                val screens = listOf(RoomNav.Home, RoomNav.Write, RoomNav.Message, RoomNav.User)
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry?.destination

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentDestination?.route !in listOf(
                                "login", "find_pw", "result_pw", "sign_up", "user_info", "chat",
                                "home_mate", "home_mate2", "home_mate4", "home_mate5",
                                "my_post", "scrap")) {
                            BottomNavigationBar(navController = navController, screens = screens)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("login") { LoginScreen(navController = navController) }
                        composable("find_pw") { FindPwScreen(navController = navController) }
                        composable("result_pw") { PwResultScreen(navController = navController) }
                        composable("sign_up") { SignUpScreen(navController = navController) }
                        composable("user_info") { UserInfoScreen(navController = navController) }
                        composable(
                            "user_edit?school={school}&name={name}&budget={budget}&houseType={houseType}&numberOfResidents={numberOfResidents}&durationOfStay={durationOfStay}&gender={gender}&lifestyle={lifestyle}&smoking={smoking}",
                            arguments = listOf(
                                navArgument("school") { type = NavType.StringType },
                                navArgument("name") { type = NavType.StringType },
                                navArgument("budget") { type = NavType.StringType },
                                navArgument("houseType") { type = NavType.StringType },
                                navArgument("numberOfResidents") { type = NavType.StringType },
                                navArgument("durationOfStay") { type = NavType.StringType },
                                navArgument("gender") { type = NavType.StringType },
                                navArgument("lifestyle") { type = NavType.StringType },
                                navArgument("smoking") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            UserEditScreen(
                                navController = navController,
                                school = backStackEntry.arguments?.getString("school") ?: "",
                                name = backStackEntry.arguments?.getString("name") ?: "",
                                budget = backStackEntry.arguments?.getString("budget") ?: "",
                                houseType = backStackEntry.arguments?.getString("houseType") ?: "",
                                numberOfResidents = backStackEntry.arguments?.getString("numberOfResidents") ?: "",
                                durationOfStay = backStackEntry.arguments?.getString("durationOfStay") ?: "",
                                gender = backStackEntry.arguments?.getString("gender") ?: "",
                                lifestyle = backStackEntry.arguments?.getString("lifestyle") ?: "",
                                smoking = backStackEntry.arguments?.getString("smoking") ?: ""
                            )
                        }
                        composable("chat") { ChatScreen(navController = navController) }

                        composable("home_mate") { DetailScreen(navController = navController, scrapViewModel = scrapViewModel) }
                        composable("home_mate2") { DetailScreen2(navController = navController, scrapViewModel = scrapViewModel) }
                        composable("home_mate4") { DetailScreen4(navController = navController, scrapViewModel = scrapViewModel) }
                        composable("home_mate5") { DetailScreen5(navController = navController, scrapViewModel = scrapViewModel) }

                        composable("my_post") { MyPostScreen(navController = navController) }
                        composable("scrap") { ScrapListScreen(navController = navController, scrapViewModel = scrapViewModel) }
                        composable("home2") { HomeScreen2(navController = navController) }

                        composable(RoomNav.Home.route) { HomeScreen(navController) }
                        composable(RoomNav.Write.route) { WriteScreen(navController) }
                        composable(RoomNav.Message.route) { backStackEntry ->
                            val lastMessage = backStackEntry.arguments?.getString("lastMessage") ?: ""
                            MessageScreen(navController = navController, lastMessage = lastMessage)
                        }
                        composable(RoomNav.User.route) { UserScreen(navController = navController) }
                    }
                }
            }
        }

        splashScreen.setKeepOnScreenCondition {
            Handler(Looper.getMainLooper()).postDelayed({
                splashScreen.setKeepOnScreenCondition { false }
            }, 2500)
            true
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    screens: List<RoomNav>
) {
    val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route

    BottomNavigation(backgroundColor = OffWhite) {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = screen.icon),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                },
                selected = currentRoute == screen.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BtnBlack,
                    unselectedIconColor = BtnBeige,
                    indicatorColor = Color.Transparent
                ),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    RoomfitTheme {
        val navController = rememberNavController()
        val screens = listOf(RoomNav.Home, RoomNav.Write, RoomNav.Message, RoomNav.User)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(navController = navController, screens = screens)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = RoomNav.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(RoomNav.Home.route) { HomeScreen(navController) }
                composable(RoomNav.Write.route) { WriteScreen(navController) }
                composable(RoomNav.Message.route) { backStackEntry ->
                    val lastMessage = backStackEntry.arguments?.getString("lastMessage") ?: ""
                    MessageScreen(navController = navController, lastMessage = lastMessage)
                }
                composable(RoomNav.User.route) { UserScreen(navController = navController) }
            }
        }
    }
}