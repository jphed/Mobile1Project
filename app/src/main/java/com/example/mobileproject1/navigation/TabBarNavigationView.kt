package com.example.mobileproject1.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.mobileproject1.IMC.views.BmiScreen
import com.example.mobileproject1.ids.IdsView
import com.example.mobileproject1.firstpartial.FirstPartialView
import com.example.mobileproject1.secondpartial.SecondPartialView
import com.example.mobileproject1.sum.views.SumBox
import com.example.mobileproject1.temperatura.views.TemperatureScreen
import com.example.mobileproject1.thirdpartial.ThirdPartialView

@Composable
fun TabBarNavigationView(navController: NavHostController = rememberNavController()) {
    val tabs = listOf(
        ScreenNavigation.Ids,
        ScreenNavigation.FirstPartial,
        ScreenNavigation.SecondPartial,
        ScreenNavigation.ThirdPartial
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .shadow(8.dp, RoundedCornerShape(30.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    tabs.forEach { screen ->
                        val selected = currentRoute == screen.route
                        val backgroundColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else Color.Transparent
                        val contentColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .clip(CircleShape)
                                .background(backgroundColor)
                                .clickable {
                                    if (!selected) {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                }
                                .padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.label,
                                tint = contentColor,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            NavHost(
                navController = navController,
                startDestination = ScreenNavigation.Ids.route
            ) {
                composable(ScreenNavigation.Ids.route) { IdsView(navController) }
                composable(ScreenNavigation.FirstPartial.route) { FirstPartialView() }
                composable(ScreenNavigation.SecondPartial.route) { SecondPartialView() }
                composable(ScreenNavigation.ThirdPartial.route) { ThirdPartialView() }
                composable(ScreenNavigation.SUM.route) { SumBox() }
                composable(ScreenNavigation.IMC.route) { BmiScreen() }
                composable(ScreenNavigation.temperatura.route) { TemperatureScreen() }
            }
        }
    }
}
