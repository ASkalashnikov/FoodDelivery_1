package com.kalashnikov.testtask.domain.rcviewitems

import com.kalashnikov.testtask.data.repository.AppData
import com.kalashnikov.testtask.domain.adapter.MainData

class RcViewMain {

    fun init(): ArrayList<MainData> {
        val list = ArrayList<MainData>()

        for (i in AppData.image_menu_cap.indices) {
            val data = MainData(AppData.image_menu_cap[i])
            list.add(data)
        }
        return list
    }
}