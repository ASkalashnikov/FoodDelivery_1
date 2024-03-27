package com.kalashnikov.testtask.domain.rcviewitems

import com.kalashnikov.testtask.data.repository.AppData
import com.kalashnikov.testtask.domain.adapter.TagsData

class RcViewTags {

    fun init(): ArrayList<TagsData> {
        val list = ArrayList<TagsData>()

        for (i in AppData.tagsList.indices) {
            val data = TagsData(AppData.tagsList[i])
            list.add(data)
        }
        return list
    }
}