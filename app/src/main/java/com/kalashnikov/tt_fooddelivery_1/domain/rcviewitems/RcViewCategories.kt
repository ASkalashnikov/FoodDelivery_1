package com.kalashnikov.tt_fooddelivery_1.domain.rcviewitems

import com.kalashnikov.tt_fooddelivery_1.data.AppData
import com.kalashnikov.tt_fooddelivery_1.domain.model.CategoriesModel
import javax.inject.Inject

class RcViewCategories @Inject constructor() {

    fun init(adapterPosition: Int): ArrayList<CategoriesModel> {
        val list = ArrayList<CategoriesModel>()

        // Сортировка всегда выполняется по тегу "Все меню"
        for (i in AppData.tags.indices) {

            for (p in AppData.tags[i].indices) {

                if (AppData.tagsList[adapterPosition] == AppData.tags[i][p]) {

                    val data = CategoriesModel(
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