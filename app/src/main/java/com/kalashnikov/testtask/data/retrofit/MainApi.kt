package com.kalashnikov.testtask.data.retrofit

import retrofit2.http.GET

interface MainApi {
    @GET("v3/058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getMainApi() : MainApiData
}