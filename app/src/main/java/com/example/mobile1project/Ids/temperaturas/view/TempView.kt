import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile1project.R
import com.example.mobile1project.Ids.temperaturas.viemodels.TempViewModel

@Composable
fun TempView(viewModel: TempViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )

        {
            Image(
                painter = painterResource(id =  R.drawable.termometro),
                contentDescription = "termometro",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.temperatura),
                style = MaterialTheme.typography.headlineSmall
            )

            OutlinedTextField(
                value = viewModel.celsius.value,
                onValueChange = { viewModel.celsius.value = it },
                label = { Text(stringResource(id = R.string.celcius)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = { viewModel.conversion() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.Calcular))
            }

            viewModel.resultado.value?.let { result ->
                Text(
                    text = stringResource(id = R.string.temperatura) + ": ${"%.2f".format(result.farenheitresult)} Farenheit",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

