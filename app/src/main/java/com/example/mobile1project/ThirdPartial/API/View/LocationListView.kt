package com.example.mobile1project.ThirdPartial.API.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile1project.ThirdPartial.API.ViewModel.LocationViewModel
import com.example.mobile1project.ThirdPartial.API.Model.Location

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationListView(viewModel: LocationViewModel = viewModel()) {
    val locations = viewModel.locations.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Locations List") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(locations.value) { location ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                           painter = painterResource(id = getDrawableId(location.imageUrl.lowercase())),
                           contentDescription = null,
                           modifier = Modifier.size(64.dp),
                           contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = "Name: ${location.name}")
                            Text(text = "Address: ${location.address}")
                            Text(text = "Lat: ${location.latitude}, Lng: ${location.longitude}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getDrawableId(imageName: String): Int {
    val context = LocalContext.current
    return context.resources.getIdentifier(imageName, "drawable", context.packageName)
}
