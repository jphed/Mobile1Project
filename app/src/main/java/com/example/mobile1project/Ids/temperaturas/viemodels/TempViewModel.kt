package com.example.mobile1project.Ids.temperaturas.viemodels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.mobile1project.Ids.temperaturas.models.TempRes
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class TempViewModel : ViewModel() {
    var celsius = mutableStateOf("")
    var resultado = mutableStateOf<TempRes?>(null)
    var celsiusError = mutableStateOf<String?>(null)


    fun conversion() {
        val celsiusvalue = celsius.value.toDoubleOrNull()
        if (celsiusvalue != null) {
            celsiusError.value = if (celsiusvalue >= -273.15) "Ingrese temperatura valida" else null
        }

        if (celsiusvalue != null && celsiusvalue > -273.15) {
            val farenheitresult = (celsiusvalue * 1.8) + 32


            viewModelScope.launch {
                resultado.value = TempRes(farenheitresult)
            }
        } else {
            resultado.value = null
        }
    }
}

