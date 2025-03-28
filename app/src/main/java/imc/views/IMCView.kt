package imc.views

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile1project.R
import imc.viewmodels.IMCViewModel

@Composable
fun IMCView(viewModel: IMCViewModel = viewModel()) {
    val context = LocalContext.current
    val weight by viewModel.weight.collectAsState()
    val height by viewModel.height.collectAsState()
    val imcResult by viewModel.imcResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campo para el peso
        OutlinedTextField(
            value = weight,
            onValueChange = { viewModel.weight.value = it },
            label = { Text(stringResource(R.string.enter_weight)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { /* Acción de siguiente */ }
            )
        )

        // Campo para la altura
        OutlinedTextField(
            value = height,
            onValueChange = { viewModel.height.value = it },
            label = { Text(stringResource(R.string.enter_height)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Acción al terminar */ }
            )
        )

        // Botón para calcular IMC
        Button(
            onClick = { viewModel.calculateIMC(context) },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(stringResource(R.string.calculate))
        }

        // Resultado del IMC
        if (imcResult.isNotEmpty()) {
            Text(
                text = imcResult,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IMCViewPreview() {
    IMCView()
}
