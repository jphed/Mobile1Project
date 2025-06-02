package com.example.mobile1project.ProyectoFinal.View

import RestaurantViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun RestaurantView(
    viewModel: RestaurantViewModel,
    navController: NavHostController

) {
    val list = viewModel.restaurantList
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(list) { restaurant ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("detail/${restaurant.id}")
                    }
            ) {
                Column {
                    val imageResId = context.resources.getIdentifier(
                        restaurant.imageName.lowercase(),
                        "drawable",
                        context.packageName
                    )

                    if (imageResId != 0) {
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .background(Color.Gray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Imagen no disponible", color = Color.White)
                        }
                    }

                    Text(
                        restaurant.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "⭐ ${restaurant.rating}  •  ${restaurant.deliveryTime}  •  ${restaurant.deliveryCost}",
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                }
            }
        }
    }

}
