package com.kalashnikov.tt_fooddelivery_1.domain.usecase

import com.kalashnikov.tt_fooddelivery_1.data.AppData
import com.kalashnikov.tt_fooddelivery_1.domain.management.AppContext
import javax.inject.Inject

class GetTextMenuCapUseCase @Inject constructor() {

    fun execute(): String {
        return AppData.text_menu_cap[AppContext.positionRcViewMain]
    }
}