package com.kalashnikov.tt_fooddelivery_1.domain.usecase

import com.kalashnikov.tt_fooddelivery_1.data.AppData
import com.kalashnikov.tt_fooddelivery_1.domain.model.CategoriesModel
import javax.inject.Inject

class GetItemCategoriesUseCase @Inject constructor(private val appData: AppData){

    fun init(adapterPosition: Int): ArrayList<CategoriesModel> {
        val list = ArrayList<CategoriesModel>()

        // Сортировка всегда выполняется по тегу "Все меню"
        for (i in appData.tags.indices) {

            for (p in appData.tags[i].indices) {

                if (appData.tagsList[adapterPosition] == appData.tags[i][p]) {

                    val data = CategoriesModel(
                        id = appData.id[i],
                        name = appData.name[i],
                        price = appData.price[i],
                        weight = appData.weight[i],
                        image = appData.imageMenu[i],
                        description = appData.description[i]
                    )
                    list.add(data)
                }
            }
        }
        return list
    }
}