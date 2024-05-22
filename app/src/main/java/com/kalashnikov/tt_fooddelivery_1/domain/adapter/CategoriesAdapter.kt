package com.kalashnikov.tt_fooddelivery_1.domain.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.tt_fooddelivery_1.databinding.ItemCategoriesBinding
import com.kalashnikov.tt_fooddelivery_1.domain.model.CategoriesModel

class CategoriesAdapter(private val interfaceCategories: InterfaceCategories) : RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    private val listData = ArrayList<CategoriesModel>()

    class CategoriesHolder(private val binding: ItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CategoriesModel, interfaceCategories: InterfaceCategories) = with(binding) {
            textCategories.text = data.name
            imageCategories.setImageResource(data.image)

            itemView.setOnClickListener {
                interfaceCategories.onClickCategories(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(inflater, parent, false)
        return CategoriesHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.bind(listData[position], interfaceCategories)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(list: List<CategoriesModel>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    interface InterfaceCategories {
        fun onClickCategories(data: CategoriesModel)
    }
}