package com.kalashnikov.testtask.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kalashnikov.testtask.databinding.FragmentMainBinding
import com.kalashnikov.testtask.domain.adapter.MainAdapter
import com.kalashnikov.testtask.domain.management.AppContext
import com.kalashnikov.testtask.domain.usecase.GetMain
import com.kalashnikov.testtask.domain.usecase.RcViewMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Сбрасываем значение, старое значение для тега (кнопка не активна)
        AppContext.oldIdTags = 0
        // Сбрасываем значение, новое значение для тега (кнопка активна)
        AppContext.newIdTags = 0

        // Получения текущей даты
        AppContext.mvvm.getDate()
        // Получения текущего города
        AppContext.mvvm.getCity(activity as Context)

        initMvvm()

        CoroutineScope(Dispatchers.Main).launch {
            // Записываем данные с сервера
            GetMain.execute()
            // Выводим данные на экран
            initRcView()
        }
    }

    private fun initRcView() {
        val adapter = MainAdapter()
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(activity as Context)
            rcView.adapter = adapter
            adapter.updateAdapter(RcViewMain.execute())
        }
    }

    private fun initMvvm() {
        AppContext.mvvm.textDate.observe(activity as FragmentActivity) { text ->
            binding.textDate.text = text
        }

        AppContext.mvvm.textCity.observe(activity as FragmentActivity) { text ->
            binding.textCity.text = text
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}