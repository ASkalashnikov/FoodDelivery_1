package com.kalashnikov.testtask.presentation.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kalashnikov.testtask.domain.adapter.MainData
import com.kalashnikov.testtask.domain.rcviewitems.RcViewMain
import com.kalashnikov.testtask.domain.usecase.GetCityUseCase
import com.kalashnikov.testtask.domain.usecase.GetDateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application
    private val getCityUseCase = GetCityUseCase()
    private val getDateUseCase = GetDateUseCase()
    private val rcViewMain = RcViewMain()

    val textDate = MutableLiveData<String>()
    val textCity = MutableLiveData<String>()
    val rcViewMainVM = MutableLiveData<ArrayList<MainData>>()

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

    fun getMain() {
        // Загрузка данных в RcView
        rcViewMainVM.value = rcViewMain.init()
    }
}