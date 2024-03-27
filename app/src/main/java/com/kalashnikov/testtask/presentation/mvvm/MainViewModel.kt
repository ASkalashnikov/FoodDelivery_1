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
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val getCityUseCase = GetCityUseCase()
    private val getDateUseCase = GetDateUseCase()
    private val rcViewMain = RcViewMain()

    val textDate = MutableLiveData<String>()
    val textCity = MutableLiveData<String>()
    val rcViewMainVM = MutableLiveData<ArrayList<MainData>>()

    init {
        // Загрузка данных в RcView
        rcViewMainVM.value = rcViewMain.init()

        // Получения текущей даты
        textDate.value = getDateUseCase.execute()

        // Делаем запрос о получения текущего города
        getCityUseCase.execute(application)

        // Получения текущего города
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                TimeUnit.MILLISECONDS.sleep(100)
            }
            textCity.value = getCityUseCase.city()
        }
    }
}