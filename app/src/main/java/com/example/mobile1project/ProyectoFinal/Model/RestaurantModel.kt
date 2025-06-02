package com.example.mobile1project.ProyectoFinal.Model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.google.gson.annotations.SerializedName

data class Restaurant(
    val id: Int = 0,
    val name: String,
    val rating: Double,
    @SerializedName("delivery") val deliveryTime: String,
    @SerializedName("fee") val deliveryCost: String,
    @SerializedName("imgName") val imageName: String,
    val phone: String,
    @SerializedName("webSite") val website: String,
    @SerializedName("latitude") val latitudeString: String,
    @SerializedName("longitude") val longitudeString: String
) {
    val latitude: Double get() = latitudeString.toDoubleOrNull() ?: 0.0
    val longitude: Double get() = longitudeString.toDoubleOrNull() ?: 0.0
}



interface RestaurantService {
    @GET("f1c89ab2e409c98ec618fdb9e75077bd/raw/15e41a39c9c251cad31639feabf0ce4ba131bb19/restaurants.json")
    suspend fun getRestaurants(): List<Restaurant>
}

object RetrofitClient {
    val service: RestaurantService by lazy {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/jorgegit/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestaurantService::class.java)
    }
}



