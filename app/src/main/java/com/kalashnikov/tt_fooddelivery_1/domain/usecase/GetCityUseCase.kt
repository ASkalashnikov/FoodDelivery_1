package com.kalashnikov.tt_fooddelivery_1.domain.usecase

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetCityUseCase @Inject constructor() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    suspend fun execute(context: Context): String {
        var city = ""
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        // Надо включить данные о место положения
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener {
                if (it != null) {
                    val geoCoder = Geocoder(context, Locale.getDefault())
                    val address = geoCoder.getFromLocation(it.latitude, it.longitude, 1)
                    city = address!![0].locality
                }
            }
        withContext(Dispatchers.IO) {
            TimeUnit.MILLISECONDS.sleep(200)
        }
        return city
    }
}