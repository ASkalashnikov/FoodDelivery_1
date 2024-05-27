package com.kalashnikov.tt_fooddelivery_1.domain.usecase

import com.kalashnikov.tt_fooddelivery_1.data.AppData
import com.kalashnikov.tt_fooddelivery_1.domain.model.TagsModel
import javax.inject.Inject

class GetItemTagsUseCase @Inject constructor(private val appData: AppData) {

    fun init(): ArrayList<TagsModel> {
        val list = ArrayList<TagsModel>()

        for (i in appData.tagsList.indices) {
            val data = TagsModel(appData.tagsList[i])
            list.add(data)
        }
        return list
    }
}