package com.kalashnikov.testtask.domain.usecase

import com.kalashnikov.testtask.domain.management.AppContext
import javax.inject.Inject

class GetPriceUseCase @Inject constructor() {

    fun execute(): Int {
        var price = 0
        for (i in AppContext.basket.indices) {
            price += AppContext.basket[i].quantity * AppContext.basket[i].price.toInt()
        }
        return price
    }
}