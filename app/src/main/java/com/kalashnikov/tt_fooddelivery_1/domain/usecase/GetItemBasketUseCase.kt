package com.kalashnikov.tt_fooddelivery_1.domain.usecase

import com.kalashnikov.tt_fooddelivery_1.domain.management.AppContext
import com.kalashnikov.tt_fooddelivery_1.domain.model.BasketModel
import javax.inject.Inject

class GetItemBasketUseCase @Inject constructor(){

    fun init(): ArrayList<BasketModel> {
        val list = ArrayList<BasketModel>()

        for (i in AppContext.basket.indices) {
            val data = BasketModel(
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
    fun updatePosition(adapterPosition: Int, symbol: String): ArrayList<BasketModel> {
        val list = ArrayList<BasketModel>()

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
            val data = BasketModel(
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