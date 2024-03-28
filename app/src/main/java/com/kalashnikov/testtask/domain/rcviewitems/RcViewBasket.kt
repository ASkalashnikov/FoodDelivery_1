package com.kalashnikov.testtask.domain.rcviewitems

import com.kalashnikov.testtask.domain.adapter.BasketData
import com.kalashnikov.testtask.domain.management.AppContext
import javax.inject.Inject

class RcViewBasket @Inject constructor() {

    fun init(): ArrayList<BasketData> {
        val list = ArrayList<BasketData>()

        for (i in AppContext.basket.indices) {
            val data = BasketData(
                id = AppContext.basket[i].id,
                name = AppContext.basket[i].name,
                price = AppContext.basket[i].price,
                weight = AppContext.basket[i].weight,
                image = AppContext.basket[i].image,
                quantity = AppContext.basket[i].quantity
            )
            list.add(data)
        }
        return list
    }

    // Обновляем или удаляем позицию
    fun updatePosition(adapterPosition: Int, symbol: String): ArrayList<BasketData> {
        val list = ArrayList<BasketData>()

        when(symbol) {
            "-" -> {
                AppContext.basket[adapterPosition].quantity --
            }
            "+" -> {
                AppContext.basket[adapterPosition].quantity ++
            }
        }

        // Удаляем позицию
        if (AppContext.basket[adapterPosition].quantity == 0) {
            AppContext.basket.removeAt(adapterPosition)
        }
        // Обновляем позицию
        else {
            val data = BasketData(
                id = AppContext.basket[adapterPosition].id,
                name = AppContext.basket[adapterPosition].name,
                price = AppContext.basket[adapterPosition].price,
                weight = AppContext.basket[adapterPosition].weight,
                image = AppContext.basket[adapterPosition].image,
                quantity = AppContext.basket[adapterPosition].quantity
            )
            list.add(0, data)
        }
        return list
    }
}