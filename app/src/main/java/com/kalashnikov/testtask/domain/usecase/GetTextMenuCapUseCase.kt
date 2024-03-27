package com.kalashnikov.testtask.domain.usecase

import com.kalashnikov.testtask.data.repository.AppData
import com.kalashnikov.testtask.domain.management.AppContext

class GetTextMenuCapUseCase {

    fun execute(): String {
        return AppData.text_menu_cap[AppContext.positionRcViewMain]
    }
}