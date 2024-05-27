package com.kalashnikov.tt_fooddelivery_1.presentation.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kalashnikov.tt_fooddelivery_1.domain.model.MainModel
import com.kalashnikov.tt_fooddelivery_1.domain.usecase.GetCityUseCase
import com.kalashnikov.tt_fooddelivery_1.domain.usecase.GetDateUseCase
import com.kalashnikov.tt_fooddelivery_1.domain.usecase.GetItemMainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val context: Application,
    private val getCityUseCase: GetCityUseCase,
    private val getDateUseCase: GetDateUseCase,
    private val getItemMainUseCase: GetItemMainUseCase
) : AndroidViewModel(context) {

    private val _textDate = MutableLiveData<String>()
    val textDate: LiveData<String> = _textDate

    private val _textCity = MutableLiveData<String>()
    val textCity: LiveData<String> = _textCity

    private val _rcViewMainVM = MutableLiveData<ArrayList<MainModel>>()
    val rcViewMainVM: LiveData<ArrayList<MainModel>> = _rcViewMainVM

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

    fun getMain() {
        // Загрузка данных в RcView
        _rcViewMainVM.value = getItemMainUseCase.init()
    }
}