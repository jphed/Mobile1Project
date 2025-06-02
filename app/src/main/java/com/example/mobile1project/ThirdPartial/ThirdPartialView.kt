package com.example.mobile1project.ThirdPartial

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ThirdPartialView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tercer Parcial",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate("StudentList")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lista")
        }

        // Botón de gimnasios eliminado

        Button(
            onClick = {
                navController.navigate("Examen tercer parcial")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Examen Tercer Parcial")
        }
    }
}
