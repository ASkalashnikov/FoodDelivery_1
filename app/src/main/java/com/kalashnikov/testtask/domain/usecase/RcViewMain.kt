package com.kalashnikov.testtask.domain.usecase

import com.kalashnikov.testtask.domain.adapter.MainData

object RcViewMain {

    fun execute(): ArrayList<MainData> {
        val list = ArrayList<MainData>()

        for (i in GetMain.model.сategories.indices) {
            val data = MainData(
                GetMain.model.сategories[i].id,
                GetMain.model.сategories[i].name,
                GetMain.model.сategories[i].image_url
            )
            list.add(data)
        }
        return list
    }
}