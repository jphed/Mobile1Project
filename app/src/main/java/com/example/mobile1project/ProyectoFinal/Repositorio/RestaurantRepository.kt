package com.example.mobile1project.ProyectoFinal.Repositorio

import com.example.mobile1project.ProyectoFinal.Model.Restaurant
import com.example.mobile1project.ProyectoFinal.Model.RetrofitClient

class RestaurantRepository {
    private val service = RetrofitClient.service

    suspend fun fetchRestaurants(): List<Restaurant> {
        return service.getRestaurants()
    }
}

