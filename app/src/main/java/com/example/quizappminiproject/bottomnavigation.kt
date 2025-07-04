package com.example.quizappminiproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController


data class NavItems(
    val label: String,
    val iconVector: ImageVector? = null,
    val iconRes: Int? = null // For custom XML icons
)

@Composable
fun BottomNav(navController: NavHostController) {
    val navItemsList = listOf(
        NavItems("PHP", iconRes = R.drawable.baseline_php_24), // 👈 XML icon from drawable
        NavItems("AI",iconRes = R.drawable. anthropic),
        NavItems("Android",iconRes = R.drawable.androidstudio),
        NavItems("Profile",iconRes = R.drawable.producthunt )
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemsList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        icon = {
                            if (navItem.iconRes != null) {
                                Icon(
                                    painter = painterResource(id = navItem.iconRes),
                                    contentDescription = navItem.label
                                )
                            } else {
                                Icon(
                                    imageVector = navItem.iconVector!!,
                                    contentDescription = navItem.label
                                )
                            }
                        },
                        label = { Text(navItem.label) },
                        onClick = { selectedIndex = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex = selectedIndex,
            navController = navController
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavHostController
) {
    when (selectedIndex) {
        0 -> PHP(navController, modifier)
        1 -> AI(navController, modifier)
        2 -> Android(navController, modifier)
        3 -> Profile(navController, modifier)
    }
}
