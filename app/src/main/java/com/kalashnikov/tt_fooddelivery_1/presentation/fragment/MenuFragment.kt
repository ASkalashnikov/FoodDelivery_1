package com.kalashnikov.tt_fooddelivery_1.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.tt_fooddelivery_1.R
import com.kalashnikov.tt_fooddelivery_1.databinding.FragmentMenuBinding
import com.kalashnikov.tt_fooddelivery_1.domain.adapter.CategoriesAdapter
import com.kalashnikov.tt_fooddelivery_1.domain.model.CategoriesModel
import com.kalashnikov.tt_fooddelivery_1.domain.adapter.TagsAdapter
import com.kalashnikov.tt_fooddelivery_1.presentation.dialog.DialogMenu
import com.kalashnikov.tt_fooddelivery_1.presentation.mvvm.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : Fragment(), TagsAdapter.InterfaceTags, CategoriesAdapter.InterfaceCategories {
    private lateinit var binding: FragmentMenuBinding
    private val mvvm: MenuViewModel by activityViewModels()
    private val adapterTags = TagsAdapter(this)
    private val adapterCategories = CategoriesAdapter(this)
    @Inject
    lateinit var dialog: DialogMenu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Показываем текст "Название категории"
        mvvm.getTextMenuCap()
        initView()

        // Загрузка данных
        mvvm.getTags()
        initRcViewTags()

        mvvm.getCategories(0)
        initRcViewCategories()

        binding.includeToolbar.imageExit.setOnClickListener {
            (requireActivity()).supportFragmentManager.beginTransaction()
                .replace(R.id.fLayout, MainFragment.newInstance())
                .commit()
        }
    }

    private fun initView() {
        mvvm.textMenuCap.observe(viewLifecycleOwner) { text ->
            binding.includeToolbar.textCategories.text = text
        }
    }

    private fun initRcViewTags() {
        binding.apply {
            rcViewTags.layoutManager =
                LinearLayoutManager(activity as Context, RecyclerView.HORIZONTAL, false)
            rcViewTags.adapter = adapterTags
        }
        mvvm.rcViewTagsVM.observe(viewLifecycleOwner) { adapterTags.updateAdapter(it) }
    }

    private fun initRcViewCategories() {
        binding.apply {
            rcViewCategories.layoutManager = GridLayoutManager(activity as Context, 3)
            rcViewCategories.adapter = adapterCategories
        }
        mvvm.rcViewCategoriesVM.observe(viewLifecycleOwner) { adapterCategories.updateAdapter(it) }
    }

    override fun onClickTags(adapterPosition: Int) {
        // Обновляем адаптер
        adapterTags.update()
        // Сортировка по категории
        mvvm.getCategories(adapterPosition)
    }

    override fun onClickCategories(data: CategoriesModel) {
        // Выводим диалог
        dialog.init(requireContext(), data)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MenuFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Сбрасываем позицию тегов на 0
        mvvm.resetPosition()
    }
}