import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.mobile1project.Ids.IMC.models.IMCResult
import kotlinx.coroutines.launch

class IMCViewModel : ViewModel() {
    var weight = mutableStateOf("")
    var height = mutableStateOf("")
    var result = mutableStateOf<IMCResult?>(null)
    var weightError = mutableStateOf<String?>(null)
    var heightError = mutableStateOf<String?>(null)

    fun calculateIMC() {
        val weightValue = weight.value.toDoubleOrNull()
        val heightValue = height.value.toDoubleOrNull()?.div(100) // Convertir cm a metros

        weightError.value = if (weightValue == null) "Ingrese un peso válido" else null
        heightError.value = if (heightValue == null || heightValue <= 0) "Ingrese una altura válida" else null

        if (weightValue != null && heightValue != null && heightValue > 0) {
            val imc = weightValue / (heightValue * heightValue)
            val classification = classifyIMC(imc)

            viewModelScope.launch {
                result.value = IMCResult(imc, classification)
            }
        } else {
            result.value = null
        }
    }

    private fun classifyIMC(imc: Double): String {
        return when {
            imc < 18.5 -> "El paciente se enuentra con bajo peso"
            imc in 18.5..24.9 -> "El paciente se encuentra con peso normal"
            imc in 25.0..29.9 -> "El paciente padece de Sobrepeso"
            else -> "El paciente padece de Obesidad"
        }
    }
}
