package com.kalashnikov.tt_fooddelivery_1.presentation.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalashnikov.tt_fooddelivery_1.domain.management.AppContext
import com.kalashnikov.tt_fooddelivery_1.domain.model.CategoriesModel
import com.kalashnikov.tt_fooddelivery_1.domain.model.TagsModel
import com.kalashnikov.tt_fooddelivery_1.domain.usecase.GetItemCategoriesUseCase
import com.kalashnikov.tt_fooddelivery_1.domain.usecase.GetItemTagsUseCase
import com.kalashnikov.tt_fooddelivery_1.domain.usecase.GetTextMenuCapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getTextMenuCapUseCase: GetTextMenuCapUseCase,
    private val getItemTagsUseCase: GetItemTagsUseCase,
    private val getItemCategoriesUseCase: GetItemCategoriesUseCase
): ViewModel() {

    private val _textMenuCap = MutableLiveData<String>()
    val textMenuCap: LiveData<String> = _textMenuCap

    private val _rcViewTagsVM = MutableLiveData<ArrayList<TagsModel>>()
    val rcViewTagsVM: LiveData<ArrayList<TagsModel>> = _rcViewTagsVM

    private val _rcViewCategoriesVM = MutableLiveData<ArrayList<CategoriesModel>>()
    val rcViewCategoriesVM: LiveData<ArrayList<CategoriesModel>> = _rcViewCategoriesVM

    // Показываем текст "Название категории"
    fun getTextMenuCap() {
        _textMenuCap.value = getTextMenuCapUseCase.execute()
    }

    fun getTags() {
        _rcViewTagsVM.value = getItemTagsUseCase.init()
    }

    fun getCategories(adapterPosition: Int) {
        _rcViewCategoriesVM.value = getItemCategoriesUseCase.init(adapterPosition)
    }

    fun resetPosition() {
        AppContext.positionRcViewTags = 0
    }
}