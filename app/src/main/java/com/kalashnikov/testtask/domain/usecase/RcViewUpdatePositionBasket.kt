package com.kalashnikov.testtask.domain.usecase

import android.util.Log
import com.kalashnikov.testtask.domain.adapter.BasketData
import com.kalashnikov.testtask.domain.management.AppContext

object RcViewUpdatePositionBasket {

    // Обновления в RecyclerView одной позиции
    fun execute(i: Int): ArrayList<BasketData> {
        val list = ArrayList<BasketData>()

        val data = BasketData(id = AppContext.basketList[i].id,
        name = AppContext.basketList[i].name,
        price = AppContext.basketList[i].price,
        weight = AppContext.basketList[i].weight,
        image_url = AppContext.basketList[i].image_url)

        Log.d("Test", "(RcViewPosition) Обновили позицию - $i")

        list.add(0, data)

        return list
    }
}