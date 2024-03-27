package com.kalashnikov.testtask.domain.management

import com.kalashnikov.testtask.domain.adapter.*

object AppContext {

    // MainFragment
    var positionRcViewMain = 0
    // MenuFragment
    var positionRcViewTags = 0

    // Корзина
    var basket = ArrayList<BasketData>()

    // Записываем город (Из за асинхронно потоков)
    var cityName = ""
}