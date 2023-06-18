package com.kalashnikov.testtask.presentation.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kalashnikov.testtask.databinding.FragmentBasketBinding
import com.kalashnikov.testtask.domain.adapter.BasketAdapter
import com.kalashnikov.testtask.domain.management.Function
import com.kalashnikov.testtask.domain.management.AppContext
import java.text.NumberFormat

class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Кнопка Оплатить
        binding.buttonAdd.setOnClickListener { }

        // Считаем сколько элементов в корзине и ставим им 1 штуку
        Function.basketCounter()

        // Пересчитываем сумму товара в корзине
        // и отправляем окончательную сумму на кнопку "Оплатить"
        Function.reckonPrice()

        // Выводим данные на экран
        initRcView()
        initMvvm()
    }

    private fun initRcView() {
        AppContext.basketAdapter = BasketAdapter()
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(activity as Context)
            rcView.adapter = AppContext.basketAdapter
            AppContext.basketAdapter.updateAdapter(AppContext.basketList)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initMvvm() = with(binding) {
        val nFormat = NumberFormat.getInstance()

        AppContext.mvvm.priceAll.observe(activity as FragmentActivity) { text ->
            if (text == 0) {
                buttonAdd.text = "Корзина пуста"
            } else {
                buttonAdd.text = "Оплатить ${nFormat.format(text)} ₽"
            }
        }

        AppContext.mvvm.textDate.observe(activity as FragmentActivity) { text ->
            textDate.text = text
        }

        AppContext.mvvm.textCity.observe(activity as FragmentActivity) { text ->
            textCity.text = text
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BasketFragment()
    }
}