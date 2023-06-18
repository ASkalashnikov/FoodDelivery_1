package com.kalashnikov.testtask.presentation.mvvm

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class MainViewModel : ViewModel() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    val priceAll = MutableLiveData<Int>()
    val textCategories = MutableLiveData<String>()
    val textDate = MutableLiveData<String>()
    val textCity = MutableLiveData<String>()

    // Кнопка "Оплатить"
    // default = 0
    init {
        priceAll.value = 0
    }

    // Кнопка "Оплатить"
    fun savePriceAll(price: Int) {
        priceAll.value = price
    }

    // Показываем текст "Название категории"
    fun loadTextCategories(text: String) {
        textCategories.value = text
    }

    // Получения текущей даты
    fun getDate() {
        val cal = Calendar.getInstance()
        val listCalendar = listOf(
            "Января",
            "Февраля",
            "Марта",
            "Апреля",
            "Мая",
            "Июня",
            "Июля",
            "Августа",
            "Сентября",
            "Октября",
            "Ноября",
            "Декабря"
        )
        val str = "${cal.get(Calendar.DAY_OF_MONTH)} ${listCalendar[cal.get(Calendar.MONTH)]} ${cal.get(Calendar.YEAR)}"
        textDate.value = str
    }

    // Получения текущего города
    fun getCity(context: Context) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
        // Надо включить данные о место положения
        task.addOnSuccessListener {
            if (it != null) {

                val geoCoder = Geocoder(context, Locale.getDefault())
                val address = geoCoder.getFromLocation(it.latitude, it.longitude,1)
                val cityName = address!![0].locality

                textCity.value = cityName
            }
        }
    }
}