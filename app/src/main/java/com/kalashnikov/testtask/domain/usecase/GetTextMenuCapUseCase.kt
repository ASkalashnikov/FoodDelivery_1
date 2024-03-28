package com.kalashnikov.testtask.domain.usecase

import com.kalashnikov.testtask.data.repository.AppData
import com.kalashnikov.testtask.domain.management.AppContext
import javax.inject.Inject

class GetTextMenuCapUseCase @Inject constructor() {

    fun execute(): String {
        return AppData.text_menu_cap[AppContext.positionRcViewMain]
    }
}