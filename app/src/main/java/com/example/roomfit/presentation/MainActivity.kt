package com.example.roomfit.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gdg.kakaobank.presentation.navigator.RoomNav
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.RoomfitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen() // 스플래시 화면 설치
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
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
                        composable(RoomNav.Home.route) { HomeScreen() }
                        composable(RoomNav.Write.route) { WriteScreen() }
                        composable(RoomNav.Message.route) { MessageScreen() }
                        composable(RoomNav.User.route) { UserScreen() }
                    }
                }
            }
        }

        // 스플래시 화면 유지 시간 설정 (2.5초)
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
                        modifier = Modifier.size(40.dp)
                    )
                },
                selected = currentRoute == screen.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BtnBlack,
                    unselectedIconColor = BtnBeige,
                    indicatorColor = Color.Transparent // Ripple 색상 제거
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