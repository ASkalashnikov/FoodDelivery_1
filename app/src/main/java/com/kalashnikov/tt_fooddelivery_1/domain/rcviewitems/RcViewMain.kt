package com.kalashnikov.tt_fooddelivery_1.domain.rcviewitems

import com.kalashnikov.tt_fooddelivery_1.data.AppData
import com.kalashnikov.tt_fooddelivery_1.domain.model.MainModel
import javax.inject.Inject

class RcViewMain @Inject constructor() {

    fun init(): ArrayList<MainModel> {
        val list = ArrayList<MainModel>()

        for (i in AppData.image_menu_cap.indices) {
            val data = MainModel(AppData.image_menu_cap[i])
            list.add(data)
        }
        return list
    }
}