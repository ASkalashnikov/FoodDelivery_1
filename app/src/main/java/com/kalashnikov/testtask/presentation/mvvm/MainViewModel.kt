package com.kalashnikov.testtask.presentation.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kalashnikov.testtask.domain.adapter.MainData
import com.kalashnikov.testtask.domain.rcviewitems.RcViewMain
import com.kalashnikov.testtask.domain.usecase.GetCityUseCase
import com.kalashnikov.testtask.domain.usecase.GetDateUseCase
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
    private val rcViewMain: RcViewMain
) : AndroidViewModel(context) {

    private val _textDate = MutableLiveData<String>()
    val textDate: LiveData<String> = _textDate

    private val _textCity = MutableLiveData<String>()
    val textCity: LiveData<String> = _textCity

    private val _rcViewMainVM = MutableLiveData<ArrayList<MainData>>()
    val rcViewMainVM: LiveData<ArrayList<MainData>> = _rcViewMainVM

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
        _rcViewMainVM.value = rcViewMain.init()
    }
}