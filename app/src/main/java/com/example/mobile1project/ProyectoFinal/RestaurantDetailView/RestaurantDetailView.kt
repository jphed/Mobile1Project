package com.example.mobile1project.ProyectoFinal.RestaurantDetailView

import RestaurantViewModel
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun RestaurantDetailsView(
    restaurantId: String?,
    viewModel: RestaurantViewModel,
    navController: NavHostController
) {
    val id = restaurantId?.toIntOrNull()
    val restaurantList = viewModel.restaurantList

    if (restaurantList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val restaurant = restaurantList.find { it.id == id }
    val context = LocalContext.current

    if (restaurant != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val imageResId = getDrawableResIdByName(restaurant.imageName)

            if (imageResId != 0) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Imagen no disponible", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(restaurant.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("⭐ ${restaurant.rating}")
            Text("Costo de envío: ${restaurant.deliveryCost}")
            Text("Tiempo estimado: ${restaurant.deliveryTime}")

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    val callIntent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:${restaurant.phone}")
                    }
                    context.startActivity(callIntent)
                }) {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Llamar")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Llamar")
                }

                Button(onClick = {
                    val websiteIntent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(restaurant.website)
                    }
                    context.startActivity(websiteIntent)
                }) {
                    Icon(imageVector = Icons.Default.Language, contentDescription = "Sitio Web")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sitio Web")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Ubicación:", fontWeight = FontWeight.Bold)

            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(
                    LatLng(restaurant.latitude, restaurant.longitude),
                    15f
                )
            }

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(
                        position = LatLng(restaurant.latitude, restaurant.longitude)
                    ),
                    title = restaurant.name
                )
            }
        }
    } else {
        Text("Restaurante no encontrado", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun getDrawableResIdByName(name: String): Int {
    val context = LocalContext.current
    return context.resources.getIdentifier(name.lowercase(), "drawable", context.packageName)
}
