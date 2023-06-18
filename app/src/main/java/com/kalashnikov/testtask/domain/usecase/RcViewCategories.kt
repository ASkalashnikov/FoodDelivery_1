package com.kalashnikov.testtask.domain.usecase

import com.kalashnikov.testtask.domain.adapter.CategoriesData
import com.kalashnikov.testtask.domain.management.AppContext

object RcViewCategories {

    fun execute(): ArrayList<CategoriesData> {
        val list = ArrayList<CategoriesData>()

        for (i in GetCategories.model.dishes.indices) {
            val data = CategoriesData(
                GetCategories.model.dishes[i].id,
                GetCategories.model.dishes[i].name,
                GetCategories.model.dishes[i].price,
                GetCategories.model.dishes[i].weight,
                GetCategories.model.dishes[i].description,
                GetCategories.model.dishes[i].image_url
            )
            list.add(data)
        }

        AppContext.listAll.clear()
        AppContext.listAll.addAll(list)

        return list
    }
}