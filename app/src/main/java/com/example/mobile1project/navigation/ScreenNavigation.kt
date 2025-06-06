package com.example.mobile1project.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenNavigation(val route: String, val label: String, val icon: ImageVector) {
    object Ids : ScreenNavigation("IdsRoute", "Inicio", Icons.Default.Home)
    object FirstPartial : ScreenNavigation("FirstPartialRoute", "Parcial 1", Icons.Default.Event)
    object SecondPartial : ScreenNavigation("SecondPartialRoute", "Parcial 2", Icons.Default.Event)
    object ThirdPartial : ScreenNavigation("ThirdPartialRoute", "Parcial 3", Icons.Default.Event)
    object IMC : ScreenNavigation("IMCRoute", "IMC", Icons.Default.Event)
    object Login : ScreenNavigation("LoginRoute", "Login", Icons.Default.Event)
    object Temperature : ScreenNavigation("TemperatureRoute", "Temperature", Icons.Default.Event)
    object Final : ScreenNavigation("restaurantList", "Final", Icons.Default.Restaurant)
}