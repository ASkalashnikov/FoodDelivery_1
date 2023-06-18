package com.kalashnikov.testtask.domain.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.testtask.databinding.ItemCategoriesBinding
import com.kalashnikov.testtask.domain.management.AppContext
import com.kalashnikov.testtask.presentation.dialog.CardDialog
import com.squareup.picasso.Picasso

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    private val list = ArrayList<CategoriesData>()

    class CategoriesHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(data: CategoriesData) = with(binding) {
            textCategories.text = data.name
            Picasso.get().load(data.image_url).into(imageCategories)
        }

        init {
            binding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        override fun onClick(v: View) {
            // Показываем экран диалога
            CardDialog.initDialog(v.context, adapterPosition)

            CardDialog.imageFavourites.setOnClickListener { }
            CardDialog.imageClose.setOnClickListener { AppContext.dialog.cancel() }

            // "buttonAdd" - Кнопка активна
            var activeButton = true

            CardDialog.buttonAdd.setOnClickListener {
                var product = false

                // Проверяем корзину, был он добавлен раньше ?
                // false - не добавлен
                // true - добавлен
                for (i in AppContext.basketList.indices) {

                    if (AppContext.basketList[i].id == AppContext.listAll[adapterPosition].id) {
                        product = true
                    }
                }

                // Добавляем продукт в корзину если еще не добавлен
                if (activeButton && !product) {
                    val data = BasketData(
                        AppContext.listAll[adapterPosition].id,
                        AppContext.listAll[adapterPosition].name,
                        AppContext.listAll[adapterPosition].price,
                        AppContext.listAll[adapterPosition].weight,
                        AppContext.listAll[adapterPosition].image_url
                    )
                    AppContext.basketList.add(data)

                    // "buttonAdd" - Кнопка не активна
                    activeButton = false
                }
            }
            AppContext.dialog.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(inflater, parent, false)
        return CategoriesHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listItem: List<CategoriesData>) {
        list.clear()
        list.addAll(listItem)
        notifyDataSetChanged()
    }
}