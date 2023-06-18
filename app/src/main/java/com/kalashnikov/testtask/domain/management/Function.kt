package com.kalashnikov.testtask.domain.management

import com.kalashnikov.testtask.data.Data
import com.kalashnikov.testtask.domain.adapter.CategoriesData
import com.kalashnikov.testtask.domain.usecase.GetCategories

object Function {

    // Сортируем по тегу и обновляем адаптер
    fun tags(position: Int) {
        val list = ArrayList<CategoriesData>()

        for (i in GetCategories.model.dishes.indices) {

            for (p in GetCategories.model.dishes[i].tegs.indices) {

                if (Data.tagsList[position] == GetCategories.model.dishes[i].tegs[p]) {

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
            }
        }

        AppContext.listAll.clear()
        AppContext.listAll.addAll(list)
        // На перерисовку адаптера
        AppContext.categoriesAdapter.updateAdapter(list)
    }

    fun basketCounter() {
        for (i in AppContext.basketList.indices) {
            AppContext.basketCounterList.add(1)
        }
    }

    // Пересчитываем сумму товара в корзине
    fun reckonPrice() {
        var price = 0
        for (i in AppContext.basketList.indices) {
            price += AppContext.basketCounterList[i] * AppContext.basketList[i].price
        }
        // Отправили окончательную сумму на кнопку "Оплатить"
        AppContext.mvvm.savePriceAll(price)
    }
}