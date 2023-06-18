package com.kalashnikov.testtask.domain.usecase

import com.kalashnikov.testtask.data.retrofit.MainApi
import com.kalashnikov.testtask.data.retrofit.MainApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetMain {

    lateinit var model: MainApiData
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun execute() = withContext(Dispatchers.IO) {
        val api = retrofit.create(MainApi::class.java)
        model = api.getMainApi()
    }
}