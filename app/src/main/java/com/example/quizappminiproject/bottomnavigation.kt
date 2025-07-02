package com.example.quizappminiproject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*


data class NavItems(
    val label: String,
    val icon: ImageVector
)
@Composable
fun BottomNav(navController: NavHostController) {
    val navItemsList = listOf(
        NavItems("PHP", Icons.Default.Home),
        NavItems("AI", Icons.Default.Add),
        NavItems("Android", Icons.Default.Phone)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemsList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        icon = { Icon(navItem.icon, contentDescription = navItem.label) },
                        label = { Text(navItem.label) },
                        onClick = {
                            selectedIndex = index
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentSCreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex = selectedIndex,
            navController = navController
        )
    }
}




@Composable
fun ContentSCreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavHostController
) {
    when (selectedIndex) {
        0 -> PHP(navController, modifier)
        1 -> AI(navController, modifier)
        2 -> Android(navController, modifier)
    }
}
