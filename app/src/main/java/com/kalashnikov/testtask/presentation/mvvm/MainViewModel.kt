package com.kalashnikov.testtask.presentation.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val priceAll = MutableLiveData<Int>()
    val textCategories = MutableLiveData<String>()
    val textDate = MutableLiveData<String>()

    // Кнопка "Оплатить"
    // default = 0
    init {
        priceAll.value = 0
    }

    // Кнопка "Оплатить"
    fun savePriceAll(price: Int) {
        priceAll.value = price
    }

    // Показываем текст "Название категории"
    fun loadTextCategories(text: String) {
        textCategories.value = text
    }

    fun date(text: String) {
        textDate.value = text
    }
}