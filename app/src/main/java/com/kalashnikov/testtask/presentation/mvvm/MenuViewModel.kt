package com.kalashnikov.testtask.presentation.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalashnikov.testtask.domain.adapter.CategoriesData
import com.kalashnikov.testtask.domain.adapter.TagsData
import com.kalashnikov.testtask.domain.management.AppContext
import com.kalashnikov.testtask.domain.rcviewitems.RcViewCategories
import com.kalashnikov.testtask.domain.rcviewitems.RcViewTags
import com.kalashnikov.testtask.domain.usecase.GetTextMenuCapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getTextMenuCapUseCase: GetTextMenuCapUseCase,
    private val rcViewTags: RcViewTags,
    private val rcViewCategories: RcViewCategories
): ViewModel() {

    private val _textMenuCap = MutableLiveData<String>()
    val textMenuCap: LiveData<String> = _textMenuCap

    private val _rcViewTagsVM = MutableLiveData<ArrayList<TagsData>>()
    val rcViewTagsVM: LiveData<ArrayList<TagsData>> = _rcViewTagsVM

    private val _rcViewCategoriesVM = MutableLiveData<ArrayList<CategoriesData>>()
    val rcViewCategoriesVM: LiveData<ArrayList<CategoriesData>> = _rcViewCategoriesVM

    // Показываем текст "Название категории"
    fun getTextMenuCap() {
        _textMenuCap.value = getTextMenuCapUseCase.execute()
    }

    fun getTags() {
        _rcViewTagsVM.value = rcViewTags.init()
    }

    fun getCategories(adapterPosition: Int) {
        _rcViewCategoriesVM.value = rcViewCategories.init(adapterPosition)
    }

    fun resetPosition() {
        AppContext.positionRcViewTags = 0
    }
}