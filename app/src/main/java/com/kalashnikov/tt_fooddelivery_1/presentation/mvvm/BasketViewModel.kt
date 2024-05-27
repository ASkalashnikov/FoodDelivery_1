package com.kalashnikov.tt_fooddelivery_1.presentation.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kalashnikov.tt_fooddelivery_1.domain.model.BasketModel
import com.kalashnikov.tt_fooddelivery_1.domain.usecase.GetCityUseCase
import com.kalashnikov.tt_fooddelivery_1.domain.usecase.GetDateUseCase
import com.kalashnikov.tt_fooddelivery_1.domain.usecase.GetItemBasketUseCase
import com.kalashnikov.tt_fooddelivery_1.domain.usecase.GetPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val context: Application,
    private val getCityUseCase: GetCityUseCase,
    private val getDateUseCase: GetDateUseCase,
    private val getItemBasketUseCase: GetItemBasketUseCase,
    private val getPriceUseCase: GetPriceUseCase
): AndroidViewModel(context) {

    private val _textDate = MutableLiveData<String>()
    val textDate: LiveData<String> = _textDate

    private val _textCity = MutableLiveData<String>()
    val textCity: LiveData<String> = _textCity

    private val _rcViewBasketVM = MutableLiveData<ArrayList<BasketModel>>()
    val rcViewBasketVM: LiveData<ArrayList<BasketModel>> = _rcViewBasketVM

    private val _price = MutableLiveData<Int>()
    val price: LiveData<Int> = _price

    fun getDate() {
        // Получения текущей даты
        _textDate.value = getDateUseCase.execute()
    }

    fun getCity() {
        // Получения текущего города
        CoroutineScope(Dispatchers.Main).launch {
            _textCity.value = getCityUseCase.execute(context)
        }
    }

    // Показываем корзину
    fun getBasket() {
        _rcViewBasketVM.value = getItemBasketUseCase.init()
    }

    // Обновляем или удаляем позицию
    fun updatePositionBasket(adapterPosition: Int, symbol: String) {
        _rcViewBasketVM.value = getItemBasketUseCase.updatePosition(adapterPosition, symbol)
    }

    // Кнопка "Оплатить"
    fun showPrice() {
        _price.value = getPriceUseCase.execute()
    }
}