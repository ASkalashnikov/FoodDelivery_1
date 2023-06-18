package com.kalashnikov.testtask.domain.usecase

import android.util.Log
import com.kalashnikov.testtask.domain.adapter.BasketData
import com.kalashnikov.testtask.domain.management.Variables

object RcViewUpdatePositionBasket {

    // Обновления в RecyclerView одной позиции
    fun execute(i: Int): ArrayList<BasketData> {
        val list = ArrayList<BasketData>()

        val data = BasketData(id = Variables.basketList[i].id,
        name = Variables.basketList[i].name,
        price = Variables.basketList[i].price,
        weight = Variables.basketList[i].weight,
        image_url = Variables.basketList[i].image_url)

        Log.d("Test", "(RcViewPosition) Обновили позицию - $i")

        list.add(0, data)

        return list
    }
}