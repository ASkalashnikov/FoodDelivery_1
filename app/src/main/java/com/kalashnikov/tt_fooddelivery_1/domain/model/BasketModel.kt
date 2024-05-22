package com.kalashnikov.tt_fooddelivery_1.domain.model

data class BasketModel(
    val id: Int,
    val name: String,
    val price: String,
    val weight: String,
    val image: Int,
    var quantity: Int
)