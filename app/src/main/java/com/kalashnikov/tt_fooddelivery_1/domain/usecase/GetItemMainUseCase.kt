package com.kalashnikov.tt_fooddelivery_1.domain.usecase

import com.kalashnikov.tt_fooddelivery_1.data.AppData
import com.kalashnikov.tt_fooddelivery_1.domain.model.MainModel
import javax.inject.Inject

class GetItemMainUseCase @Inject constructor(private val appData: AppData) {

    fun init(): ArrayList<MainModel> {
        val list = ArrayList<MainModel>()

        for (i in appData.imageMenuCap.indices) {
            val data = MainModel(appData.imageMenuCap[i])
            list.add(data)
        }
        return list
    }
}