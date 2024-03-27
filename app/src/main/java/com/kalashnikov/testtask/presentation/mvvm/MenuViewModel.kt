package com.kalashnikov.testtask.presentation.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalashnikov.testtask.domain.adapter.CategoriesData
import com.kalashnikov.testtask.domain.adapter.TagsData
import com.kalashnikov.testtask.domain.management.AppContext
import com.kalashnikov.testtask.domain.rcviewitems.RcViewCategories
import com.kalashnikov.testtask.domain.rcviewitems.RcViewTags
import com.kalashnikov.testtask.domain.usecase.GetTextMenuCapUseCase

class MenuViewModel: ViewModel() {

    private val getTextMenuCapUseCase = GetTextMenuCapUseCase()
    private val rcViewTags = RcViewTags()
    private val rcViewCategories = RcViewCategories()

    val textMenuCap = MutableLiveData<String>()
    val rcViewTagsVM = MutableLiveData<ArrayList<TagsData>>()
    val rcViewCategoriesVM = MutableLiveData<ArrayList<CategoriesData>>()

    // Показываем текст "Название категории"
    fun getTextMenuCap() {
        textMenuCap.value = getTextMenuCapUseCase.execute()
    }

    fun getTags() {
        rcViewTagsVM.value = rcViewTags.init()
    }

    fun getCategories(adapterPosition: Int) {
        rcViewCategoriesVM.value = rcViewCategories.init(adapterPosition)
    }

    fun resetPosition() {
        AppContext.positionRcViewTags = 0
    }
}