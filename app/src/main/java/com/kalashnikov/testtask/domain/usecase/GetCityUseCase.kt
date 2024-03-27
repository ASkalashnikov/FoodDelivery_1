package com.kalashnikov.testtask.domain.usecase

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kalashnikov.testtask.domain.management.AppContext
import java.util.*

class GetCityUseCase {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    fun execute(context: Context) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        // Надо включить данные о место положения
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener {
            if (it != null) {
                val geoCoder = Geocoder(context, Locale.getDefault())
                val address = geoCoder.getFromLocation(it.latitude, it.longitude, 1)
                AppContext.cityName = address!![0].locality
            }
        }
    }

    fun city(): String {
        return AppContext.cityName
    }
}