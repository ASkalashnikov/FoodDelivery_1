package com.kalashnikov.testtask.domain.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.testtask.databinding.ItemCategoriesBinding

class CategoriesAdapter(private val interfaceCategories: InterfaceCategories) : RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    private val listData = ArrayList<CategoriesData>()

    class CategoriesHolder(private val binding: ItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CategoriesData, interfaceCategories: InterfaceCategories) = with(binding) {
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
    fun updateAdapter(list: List<CategoriesData>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    interface InterfaceCategories {
        fun onClickCategories(data: CategoriesData)
    }
}