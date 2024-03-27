package com.kalashnikov.testtask.data.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetCategories {

    lateinit var model: CategoriesApiData
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun execute() = withContext(Dispatchers.IO) {
        val api = retrofit.create(CategoriesApi::class.java)
        model = api.getCategoriesApi()
    }
}