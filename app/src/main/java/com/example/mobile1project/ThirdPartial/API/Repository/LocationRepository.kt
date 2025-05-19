package com.example.mobile1project.ThirdPartial.API.Repository

import com.example.mobile1project.ThirdPartial.API.Model.Location
import com.example.mobile1project.ThirdPartial.API.Network.LocationApiService

class LocationRepository(private val apiService: LocationApiService) {
    suspend fun fetchLocations(): List<Location> {
        return apiService.getLocations()
    }
}