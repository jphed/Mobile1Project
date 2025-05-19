package com.example.mobile1project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.TextFieldValue

class SumaViewModel : ViewModel() {
    var num1 by mutableStateOf("")
    var num2 by mutableStateOf("")
    var resultado by mutableStateOf("")

    fun sumar() {
        val valor1 = num1.toDoubleOrNull() ?: 0.0
        val valor2 = num2.toDoubleOrNull() ?: 0.0
        resultado = (valor1 + valor2).toString()
    }
}

@Composable
fun SumaApp(viewModel: SumaViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = viewModel.num1,
            onValueChange = { viewModel.num1 = it },
            label = { Text("Número 1") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.num2,
            onValueChange = { viewModel.num2 = it },
            label = { Text("Número 2") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.sumar() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sumar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Resultado: ${viewModel.resultado}", style = MaterialTheme.typography.headlineSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSumaApp() {
    SumaApp()
}
