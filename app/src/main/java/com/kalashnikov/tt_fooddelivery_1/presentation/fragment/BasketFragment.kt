package com.kalashnikov.tt_fooddelivery_1.presentation.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kalashnikov.tt_fooddelivery_1.databinding.FragmentBasketBinding
import com.kalashnikov.tt_fooddelivery_1.domain.adapter.BasketAdapter
import com.kalashnikov.tt_fooddelivery_1.presentation.mvvm.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat

@AndroidEntryPoint
class BasketFragment : Fragment(), BasketAdapter.InterfaceBasket {
    private lateinit var binding: FragmentBasketBinding
    private val mvvm: BasketViewModel by activityViewModels()
    private val adapter = BasketAdapter(this)
    private var position = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Загружаем данные в LiveData
        mvvm.getCity()
        mvvm.getDate()
        initView()

        mvvm.getBasket()
        initRcView()
        mvvm.showPrice()
        initPrice()
    }

    private fun initView() = with(binding){
        mvvm.textDate.observe(viewLifecycleOwner) { text ->
            includeToolbar.textDate.text = text
        }
        mvvm.textCity.observe(viewLifecycleOwner) { text ->
            includeToolbar.textCity.text = text
        }
    }

    private fun initRcView() {
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(activity as Context)
            rcView.adapter = adapter
        }

        mvvm.rcViewBasketVM.observe(viewLifecycleOwner) {
            // Обновляем весь адаптер
            if (position == -1) {
                adapter.updateAdapter(it)
            } else {
                // Обновляем или удаляем позицию
                if (it.isNotEmpty()) {
                    adapter.updatePosition(position, it)
                } else {
                    adapter.deletePosition(position)
                }
            }
            // Обновляем цену на кнопке "Оплатить"
            mvvm.showPrice()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initPrice() = with(binding) {
        val nFormat = NumberFormat.getInstance()

        mvvm.price.observe(activity as FragmentActivity) { price ->
            if (price == 0) {
                buttonAdd.text = "Корзина пуста"
            } else {
                buttonAdd.text = "Оплатить ${nFormat.format(price)} ₽"
            }
        }
    }

    override fun onClickBasketMinus(adapterPosition: Int) {
        position = adapterPosition
        mvvm.updatePositionBasket(adapterPosition, "-")
    }

    override fun onClickBasketPlus(adapterPosition: Int) {
        position = adapterPosition
        mvvm.updatePositionBasket(adapterPosition, "+")
    }

    companion object {
        @JvmStatic
        fun newInstance() = BasketFragment()
    }
}