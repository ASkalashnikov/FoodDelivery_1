package com.kalashnikov.testtask.domain.management

import com.kalashnikov.testtask.data.Data
import com.kalashnikov.testtask.domain.adapter.CategoriesData
import com.kalashnikov.testtask.domain.usecase.GetCategories
import java.util.*
import kotlin.collections.ArrayList

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

        Variables.listAll.clear()
        Variables.listAll.addAll(list)
        // На перерисовку адаптера
        Variables.categoriesAdapter.updateAdapter(list)
    }

    fun basketCounter() {
        for (i in Variables.basketList.indices) {
            Variables.basketCounterList.add(1)
        }
    }

    // Пересчитываем сумму товара в корзине
    fun reckonPrice() {
        var price = 0
        for (i in Variables.basketList.indices) {
            price += Variables.basketCounterList[i] * Variables.basketList[i].price
        }
        // Отправили окончательную сумму на кнопку "Оплатить"
        Variables.mvvm.savePriceAll(price)
    }

    fun getDateCalendar() {
        val cal = Calendar.getInstance()

        val listCalendar = listOf(
            "Января",
            "Февраля",
            "Марта",
            "Апреля",
            "Мая",
            "Июня",
            "Июля",
            "Августа",
            "Сентября",
            "Октября",
            "Ноября",
            "Декабря"
        )

        Variables.mvvm.date(
            "${cal.get(Calendar.DAY_OF_MONTH)} " +
                    "${listCalendar[cal.get(Calendar.MONTH)]} " +
                    "${cal.get(Calendar.YEAR)}"
        )
    }
}