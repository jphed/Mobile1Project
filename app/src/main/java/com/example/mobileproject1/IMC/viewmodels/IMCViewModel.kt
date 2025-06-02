package com.example.mobileproject1.IMC.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {

    var bmi by mutableStateOf("")
        private set

    var message by mutableStateOf("")
        private set

    fun calculateBmi(weightKg: String, heightM: String) {
        val weight = weightKg.toFloatOrNull()
        val height = heightM.toFloatOrNull()

        if (weight == null || height == null || height <= 0) {
            bmi = "Entrada inválida"
            message = ""
            return
        }

        val bmiValue = weight / (height * height)
        bmi = String.format("%.2f", bmiValue)

        message = if (bmiValue in 19.0..24.9) {
            "Peso saludable"
        } else {
            "Peso no saludable"
        }
    }
}
