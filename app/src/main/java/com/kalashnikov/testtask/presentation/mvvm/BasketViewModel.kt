package com.kalashnikov.testtask.presentation.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kalashnikov.testtask.domain.adapter.BasketData
import com.kalashnikov.testtask.domain.rcviewitems.RcViewBasket
import com.kalashnikov.testtask.domain.usecase.GetCityUseCase
import com.kalashnikov.testtask.domain.usecase.GetDateUseCase
import com.kalashnikov.testtask.domain.usecase.GetPriceUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketViewModel(application: Application): AndroidViewModel(application) {

    private val context = application
    private val getCityUseCase = GetCityUseCase()
    private val getDateUseCase = GetDateUseCase()
    private val rcViewBasket = RcViewBasket()
    private val getPriceUseCase = GetPriceUseCase()

    val textDate = MutableLiveData<String>()
    val textCity = MutableLiveData<String>()
    val rcViewBasketVM = MutableLiveData<ArrayList<BasketData>>()
    val price = MutableLiveData<Int>()

    fun getDate() {
        // Получения текущей даты
        textDate.value = getDateUseCase.execute()
    }

    fun getCity() {
        // Получения текущего города
        CoroutineScope(Dispatchers.Main).launch {
            textCity.value = getCityUseCase.execute(context)
        }
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