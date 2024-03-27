package com.kalashnikov.testtask.domain.adapter

data class BasketData(
    val id: Int,
    val name: String,
    val price: String,
    val weight: String,
    val image: Int,
    var quantity: Int
)