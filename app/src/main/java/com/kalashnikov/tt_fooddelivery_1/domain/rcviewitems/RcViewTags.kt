package com.kalashnikov.tt_fooddelivery_1.domain.rcviewitems

import com.kalashnikov.tt_fooddelivery_1.data.AppData
import com.kalashnikov.tt_fooddelivery_1.domain.model.TagsModel
import javax.inject.Inject

class RcViewTags @Inject constructor() {

    fun init(): ArrayList<TagsModel> {
        val list = ArrayList<TagsModel>()

        for (i in AppData.tagsList.indices) {
            val data = TagsModel(AppData.tagsList[i])
            list.add(data)
        }
        return list
    }
}