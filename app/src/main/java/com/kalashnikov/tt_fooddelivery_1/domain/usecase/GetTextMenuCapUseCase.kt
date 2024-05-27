package com.kalashnikov.tt_fooddelivery_1.domain.usecase

import com.kalashnikov.tt_fooddelivery_1.data.AppData
import com.kalashnikov.tt_fooddelivery_1.domain.management.AppContext
import javax.inject.Inject

class GetTextMenuCapUseCase @Inject constructor(private val appData: AppData) {

    fun execute(): String {
        return appData.textMenuCap[AppContext.positionRcViewMain]
    }
}