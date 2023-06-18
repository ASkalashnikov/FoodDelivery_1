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
import com.kalashnikov.testtask.domain.management.Variables
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
        Variables.basketAdapter = BasketAdapter()
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(activity as Context)
            rcView.adapter = Variables.basketAdapter
            Variables.basketAdapter.updateAdapter(Variables.basketList)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initMvvm() {
        val nFormat = NumberFormat.getInstance()

        Variables.mvvm.priceAll.observe(activity as FragmentActivity) { text ->
            if (text == 0) {
                binding.buttonAdd.text = "Корзина пуста"
            } else {
                binding.buttonAdd.text = "Оплатить ${nFormat.format(text)} ₽"
            }
        }

        Variables.mvvm.textDate.observe(activity as FragmentActivity) { text ->
            binding.textDate.text = text
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BasketFragment()
    }
}