package com.kalashnikov.testtask.domain.management

import android.app.Dialog
import com.kalashnikov.testtask.domain.adapter.*
import com.kalashnikov.testtask.presentation.mvvm.MainViewModel

object Variables {

    lateinit var mvvm: MainViewModel
    lateinit var dialog: Dialog
    lateinit var categoriesAdapter: CategoriesAdapter
    lateinit var tagsAdapter: TagsAdapter
    lateinit var basketAdapter: BasketAdapter

    // Старое значение для тега (кнопка не активна)
    var oldIdTags = 0
    // Новое значение для тега (кнопка активна)
    var newIdTags = 0

    // Для сортировки по тегам
    val listAll = ArrayList<CategoriesData>()

    // То что добавили в корзину
    val basketList = ArrayList<BasketData>()
    // Количество штук в корзине
    val basketCounterList = ArrayList<Int>()
}