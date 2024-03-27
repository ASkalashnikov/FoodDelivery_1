package com.kalashnikov.testtask.presentation.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalashnikov.testtask.domain.adapter.BasketData
import com.kalashnikov.testtask.domain.rcviewitems.RcViewBasket
import com.kalashnikov.testtask.domain.usecase.GetCityUseCase
import com.kalashnikov.testtask.domain.usecase.GetDateUseCase
import com.kalashnikov.testtask.domain.usecase.GetPriceUseCase

class BasketViewModel: ViewModel() {

    private val getCityUseCase = GetCityUseCase()
    private val getDateUseCase = GetDateUseCase()
    private val rcViewBasket = RcViewBasket()
    private val getPriceUseCase = GetPriceUseCase()

    val textDate = MutableLiveData<String>()
    val textCity = MutableLiveData<String>()
    val rcViewBasketVM = MutableLiveData<ArrayList<BasketData>>()
    val price = MutableLiveData<Int>()

    init {
        // Получения текущей даты
        textDate.value = getDateUseCase.execute()

        // Получения текущего города
        textCity.value = getCityUseCase.city()
    }

    // Показываем корзину
    fun getBasket() {
        rcViewBasketVM.value = rcViewBasket.init()
    }

    // Обновляем или удаляем позицию
    fun updatePositionBasket(adapterPosition: Int, symbol: String) {
        rcViewBasketVM.value = rcViewBasket.updatePosition(adapterPosition, symbol)
    }

    // Кнопка "Оплатить"
    fun showPrice() {
        price.value = getPriceUseCase.execute()
    }
}