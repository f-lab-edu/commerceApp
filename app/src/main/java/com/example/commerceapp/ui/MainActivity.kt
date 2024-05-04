package com.example.commerceapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.commerceapp.R
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
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") { HomeScreen() }
            composable("search") { SearchScreen() }
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
            .padding(horizontal = 16.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavigationItem(
            selected = selectedRoute == "home",
            onClick = { navController.navigate("home") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic__home),
                    contentDescription = "Home"
                )
            },
            label = { Text("홈", fontSize = 10.sp) }
        )
        BottomNavigationItem(
            selected = selectedRoute == "search",
            onClick = { navController.navigate("search") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic__search),
                    contentDescription = "Search"
                )
            },
            label = { Text("검색", fontSize = 10.sp) }
        )
        BottomNavigationItem(
            selected = selectedRoute == "profile",
            onClick = { navController.navigate("profile") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic__user),
                    contentDescription = "Profile"
                )
            },
            label = { Text("프로필", fontSize = 10.sp) }
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
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = Modifier.background(Color.White),
        colors = ButtonColors(contentColor = Color.Black, containerColor = Color.White, disabledContainerColor = Color.White, disabledContentColor = Color.LightGray)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 4.dp)
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