package com.kalashnikov.testtask.domain.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.testtask.R
import com.kalashnikov.testtask.databinding.ItemBasketBinding
import com.kalashnikov.testtask.domain.management.Variables
import com.kalashnikov.testtask.domain.usecase.RcViewUpdatePositionBasket
import com.squareup.picasso.Picasso
import com.kalashnikov.testtask.domain.management.Function

class BasketAdapter: RecyclerView.Adapter<BasketAdapter.BasketHolder>() {
    private val list = ArrayList<BasketData>()

    class BasketHolder(private val binding: ItemBasketBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        @SuppressLint("SetTextI18n")
        fun bind(data: BasketData) = with(binding) {
            Picasso.get().load(data.image_url).into(imageBasket)
            textName.text = data.name
            textPrice.text = "${data.price} ₽ "
            textWeight.text = "· ${data.weight}г"

            // Показываем количество штук у одного продукта
            textCounter.text = Variables.basketCounterList[adapterPosition].toString()
        }

        init {
            binding.root.setOnClickListener(this)
            binding.buttonMinus.setOnClickListener(this)
            binding.buttonPlus.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            when (v.id) {
                R.id.buttonMinus -> {
                    // Отнимает один элемент у данной позиции
                    Variables.basketCounterList[adapterPosition] -= 1

                    // Удаляем элемент в корзине если число штук равна нулю
                    if (Variables.basketCounterList[adapterPosition] == 0) {

                        Variables.basketList.removeAt(adapterPosition)
                        Variables.basketCounterList.removeAt(adapterPosition)
                        Variables.basketAdapter.deletePosition(adapterPosition)
                    }
                }

                R.id.buttonPlus -> {
                    // Прибавляем один элемент у данной позиции
                    Variables.basketCounterList[adapterPosition] += 1
                }
            }

            // Если в корзине 0 товара и позиция была удалена (-1), то не обновляем адаптер
            if (Variables.basketList.size >= 1 && adapterPosition >= 0) {
                // Обновляем одну позицию
                Variables.basketAdapter.updatePosition(adapterPosition, RcViewUpdatePositionBasket.execute(adapterPosition))
            }

            // Пересчитываем сумму товара в корзине
            // и отправляем окончательную сумму на кнопку "Оплатить"
            Function.reckonPrice()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBasketBinding.inflate(inflater, parent, false)
        return BasketHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listItem: List<BasketData>) {
        list.clear()
        list.addAll(listItem)
        notifyDataSetChanged()
    }

    fun deletePosition(i: Int) {
        list.removeAt(i)
        notifyItemRemoved(i)
    }

    fun updatePosition(i: Int, listItem: List<BasketData>) {
        list.removeAt(i)
        list.add(i, listItem[0])
        notifyItemChanged(i)
    }
}