package com.kalashnikov.testtask.data.retrofit

data class MainApiData(
    val сategories: List<Main>
)

data class Main(
    val id: Int,
    val name: String,
    val image_url: String
)
