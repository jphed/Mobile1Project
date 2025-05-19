package com.example.mobile1project.navigation

import IMCScreen
import TempView
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.mobile1project.Ids.IdsView
import com.example.mobile1project.FirstPartial.FirstPartialView
import com.example.mobile1project.SecondPartial.SecondPartialView
import com.example.mobile1project.Ids.login.views.LoginView
import com.example.mobile1project.ListaEstudiantes.View.StudentView
import com.example.mobile1project.ThirdPartial.API.View.LocationListView
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.View.StudentListView
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.ViewModel.StudentViewModel
import com.example.mobile1project.ThirdPartial.ThirdPartialView

@Composable
fun TabBarNavigationView(
    navController: NavHostController = rememberNavController(),
    studentViewModel: StudentViewModel
) {
    val items = listOf(
        ScreenNavigation.Ids,
        ScreenNavigation.FirstPartial,
        ScreenNavigation.SecondPartial,
        ScreenNavigation.ThirdPartial
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer, // Fondo morado claro
                tonalElevation = 8.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.label,
                                tint = if (currentRoute == screen.route)
                                    MaterialTheme.colorScheme.onPrimaryContainer // Texto y icono claros
                                else
                                    MaterialTheme.colorScheme.primary // Púrpura oscuro para no seleccionado
                            )
                        },
                        label = {
                            Text(
                                text = screen.label,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (currentRoute == screen.route)
                                    MaterialTheme.colorScheme.onPrimaryContainer
                                else
                                    MaterialTheme.colorScheme.primary
                            )
                        },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        alwaysShowLabel = true
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Ids.route,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            composable(ScreenNavigation.Ids.route) { IdsView(navController) }
            composable(ScreenNavigation.FirstPartial.route) { FirstPartialView() }
            composable(ScreenNavigation.SecondPartial.route) { SecondPartialView() }
            composable(ScreenNavigation.ThirdPartial.route) { ThirdPartialView(navController) }
            composable(ScreenNavigation.IMC.route) { IMCScreen() }
            composable(ScreenNavigation.Login.route) { LoginView() }
            composable(ScreenNavigation.Temperature.route) { TempView() }
            composable("studentList") { StudentView() }
            composable("GymAPI") { LocationListView() }
            composable("Examen3P") { StudentListView(uiState = studentViewModel.uiState) }
        }
    }
}
