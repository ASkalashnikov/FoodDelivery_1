package com.kalashnikov.testtask.domain.rcviewitems

import com.kalashnikov.testtask.data.repository.AppData
import com.kalashnikov.testtask.domain.adapter.CategoriesData
import javax.inject.Inject

class RcViewCategories @Inject constructor() {

    fun init(adapterPosition: Int): ArrayList<CategoriesData> {
        val list = ArrayList<CategoriesData>()

        // Сортировка всегда выполняется по тегу "Все меню"
        for (i in AppData.tags.indices) {

            for (p in AppData.tags[i].indices) {

                if (AppData.tagsList[adapterPosition] == AppData.tags[i][p]) {

                    val data = CategoriesData(
                        id = AppData.id[i],
                        name = AppData.name[i],
                        price = AppData.price[i],
                        weight = AppData.weight[i],
                        image = AppData.image_menu[i],
                        description = AppData.description[i]
                    )
                    list.add(data)
                }
            }
        }
        return list
    }
}