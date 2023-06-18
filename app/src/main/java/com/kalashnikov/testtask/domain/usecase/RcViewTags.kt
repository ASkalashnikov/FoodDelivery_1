package com.kalashnikov.testtask.domain.usecase

import com.kalashnikov.testtask.data.Data
import com.kalashnikov.testtask.domain.adapter.TagsData

object RcViewTags {

    fun execute(): ArrayList<TagsData> {
        val list = ArrayList<TagsData>()

        for (i in Data.tagsList.indices) {
            val data = TagsData(Data.tagsList[i])
            list.add(data)
        }
        return list
    }
}