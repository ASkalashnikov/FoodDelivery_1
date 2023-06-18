package com.kalashnikov.testtask.data.retrofit

data class CategoriesApiData(
    val dishes: List<Categories>
)

data class Categories(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val image_url: String,
    val tegs: List<String>
)
