package com.example.commerceapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    val currentRoute = navController.currentDestination?.route ?: ""

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectedRoute = currentRoute
            )
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") { HomeScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    selectedRoute: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavigationItem(
            selected = selectedRoute == "home",
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("홈") }
        )

        BottomNavigationItem(
            selected = selectedRoute == "profile",
            onClick = { navController.navigate("profile") },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("프로필") }
        )
    }
}

@Composable
fun BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            icon()
            Spacer(modifier = Modifier.width(4.dp))
            label()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CommerceAppTheme {
        HomeScreen()
    }
}