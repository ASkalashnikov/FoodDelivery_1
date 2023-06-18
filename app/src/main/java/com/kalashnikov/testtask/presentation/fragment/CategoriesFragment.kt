package com.kalashnikov.testtask.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.testtask.R
import com.kalashnikov.testtask.databinding.FragmentCategoriesBinding
import com.kalashnikov.testtask.domain.adapter.CategoriesAdapter
import com.kalashnikov.testtask.domain.adapter.TagsAdapter
import com.kalashnikov.testtask.domain.management.AppContext
import com.kalashnikov.testtask.domain.usecase.GetCategories
import com.kalashnikov.testtask.domain.usecase.RcViewCategories
import com.kalashnikov.testtask.domain.usecase.RcViewTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            // Записываем данные с сервера
            GetCategories.execute()
            // Выводим данные на экран
            initRcView()
            initMvvm()
        }

        binding.imageExit.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fLayout, MainFragment.newInstance())
                .commit()
        }
    }

    private fun initRcView() {
        AppContext.categoriesAdapter = CategoriesAdapter()
        AppContext.tagsAdapter = TagsAdapter()

        binding.apply {

            // RcView Categories
            rcViewMenu.layoutManager = GridLayoutManager(activity as Context, 3)
            rcViewMenu.adapter = AppContext.categoriesAdapter
            AppContext.categoriesAdapter.updateAdapter(RcViewCategories.execute())

            // RcView Tags
            rcViewCategories.layoutManager =
                LinearLayoutManager(activity as Context, RecyclerView.HORIZONTAL, false)
            rcViewCategories.adapter = AppContext.tagsAdapter
            AppContext.tagsAdapter.updateAdapter(RcViewTags.execute())
        }
    }

    private fun initMvvm() {
        AppContext.mvvm.textCategories.observe(activity as FragmentActivity) { text ->
            binding.textCategories.text = text
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }
}