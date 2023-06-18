package com.kalashnikov.testtask.data.retrofit

import retrofit2.http.GET

interface CategoriesApi {
    @GET("v3/aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    suspend fun getCategoriesApi() : CategoriesApiData
}