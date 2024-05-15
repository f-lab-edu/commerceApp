package com.example.commerceapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.commerceapp.ui.theme.CommerceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommerceAppTheme {
                BottomNavigation()
            }
        }
    }
}

@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = BottomNavigationScreen.entries
            )
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = BottomNavigationScreen.Home.route
        ) {
            composable(BottomNavigationScreen.Home.route) { HomeScreen(navController) }
            composable(BottomNavigationScreen.Search.route) { SearchScreen(navController) }
            composable(BottomNavigationScreen.Profile.route) { ProfileScreen() }
            composable("product/{productNo}") { navBackStackEntry ->
                navBackStackEntry.arguments?.getString("productNo")?.let {
                    ProductScreen(
                        navController = navController,
                        productNo = it
                    )
                } ?: ErrorScreen()
            }
            composable("search/{keyword}") { navBackStackEntry ->
                navBackStackEntry.arguments?.getString("keyword")?.let {
                    SearchResultScreen(
                        navController = navController,
                        keyword = it
                    )
                } ?: ErrorScreen()
            }
        }
    }
}

@Composable
fun ErrorScreen() {
    TODO("Not yet implemented")
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavigationScreen>
) {
    val currentRoute = navController.currentDestination?.route ?: items.firstOrNull()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach {
            NavigationBarItem(
                selected = currentRoute == it.route,
                onClick = { navController.navigate(it.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = it.iconResourceId),
                        contentDescription = stringResource(id = it.stringResourceId)
                    )
                }
            )
        }
    }
}