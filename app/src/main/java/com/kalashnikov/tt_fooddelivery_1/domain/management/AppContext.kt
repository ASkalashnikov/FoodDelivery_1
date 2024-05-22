package com.kalashnikov.tt_fooddelivery_1.domain.management

import com.kalashnikov.tt_fooddelivery_1.domain.model.BasketModel

object AppContext {

    // MainFragment
    var positionRcViewMain = 0
    // MenuFragment
    var positionRcViewTags = 0

    // Корзина
    var basket = ArrayList<BasketModel>()
}