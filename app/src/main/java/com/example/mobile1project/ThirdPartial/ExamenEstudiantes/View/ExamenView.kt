package com.example.mobile1project.ThirdPartial.ExamenEstudiantes.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.Model.Student
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.Repository.Result
import kotlinx.coroutines.flow.StateFlow

@Composable
fun StudentListView(
    uiState: StateFlow<Result<List<Student>>>,
    modifier: Modifier = Modifier
) {
    val state by uiState.collectAsState()

    when (state) {
        is Result.Loading -> Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        is Result.Error -> Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text((state as Result.Error).message)
        }

        is Result.Success -> LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items((state as Result.Success<List<Student>>).data) { student ->
                StudentCard(student)
            }
        }
    }
}

@Composable
fun StudentCard(student: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ✅ Corrección: guardamos LocalContext antes del remember
            val context = LocalContext.current
            val imageRes = remember(student.imagen) {
                val fileName = student.imagen
                    .substringBeforeLast('.')   // Quitar la extensión .jpg/.jpeg
                    .lowercase()                // Convertir a minúsculas
                val id = context.resources.getIdentifier(fileName, "drawable", context.packageName)
                if (id == 0) null else id
            }

            if (imageRes != null) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = student.nombre,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                // Imagen no existe
                Icon(
                    imageVector = Icons.Default.PersonOff,
                    contentDescription = "Imagen no encontrada",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(Modifier.width(12.dp))

            Column {
                Text(student.nombre, style = MaterialTheme.typography.titleMedium)
                Text("Matrícula: ${student.matricula}")
                Text(
                    "\"${student.frase}\"",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}
