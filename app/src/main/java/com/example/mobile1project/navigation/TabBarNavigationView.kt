package com.example.mobile1project.navigation

import IMCScreen
import RestaurantViewModel
import TempView
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.mobile1project.Ids.IdsView
import com.example.mobile1project.FirstPartial.FirstPartialView
import com.example.mobile1project.SecondPartial.SecondPartialView
import com.example.mobile1project.Ids.login.views.LoginView
import com.example.mobile1project.ListaEstudiantes.View.StudentView
import com.example.mobile1project.ProyectoFinal.RestaurantDetailView.RestaurantDetailsView
import com.example.mobile1project.ProyectoFinal.View.RestaurantView
import com.example.mobile1project.ThirdPartial.API.View.LocationListView
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.View.StudentListView
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.ViewModel.StudentViewModel
import com.example.mobile1project.ThirdPartial.ThirdPartialView

@Composable
fun TabBarNavigationView(
    navController: NavHostController = rememberNavController(),
    studentViewModel: StudentViewModel,
    restaurantViewModel: RestaurantViewModel
) {
    val items = listOf(
        ScreenNavigation.Ids,
        ScreenNavigation.FirstPartial,
        ScreenNavigation.SecondPartial,
        ScreenNavigation.ThirdPartial,
        ScreenNavigation.Final
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Ids.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenNavigation.Ids.route) { IdsView(navController) }
            composable(ScreenNavigation.FirstPartial.route) { FirstPartialView() }
            composable(ScreenNavigation.SecondPartial.route) { SecondPartialView() }
            composable(ScreenNavigation.ThirdPartial.route) { ThirdPartialView(navController) }
            composable(ScreenNavigation.IMC.route) { IMCScreen() }
            composable(ScreenNavigation.Login.route) { LoginView() }
            composable(ScreenNavigation.Temperature.route) { TempView() }
            composable("studentList") { StudentView() }
            // Aquí eliminé el composable para "GymAPI"
            composable("Examen3P") { StudentListView(uiState = studentViewModel.uiState) }
            composable("RestaurantList") {
                RestaurantView(viewModel = restaurantViewModel, navController = navController)
            }
            composable("detail/{restaurantId}") { backStackEntry ->
                val restaurantId = backStackEntry.arguments?.getString("restaurantId")
                RestaurantDetailsView(
                    restaurantId = restaurantId,
                    viewModel = restaurantViewModel,
                    navController = navController
                )
            }
        }
    }
}
