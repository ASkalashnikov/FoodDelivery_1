package com.kalashnikov.tt_fooddelivery_1.domain.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.tt_fooddelivery_1.databinding.ItemTagsBinding
import com.kalashnikov.tt_fooddelivery_1.domain.management.AppContext
import com.kalashnikov.tt_fooddelivery_1.domain.model.TagsModel

class TagsAdapter(private val interfaceTags: InterfaceTags) : RecyclerView.Adapter<TagsAdapter.TagsHolder>() {
    private val list = ArrayList<TagsModel>()

    class TagsHolder(private val binding: ItemTagsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TagsModel, interfaceTags: InterfaceTags) = with(binding) {
            textTags.text = data.tags

            // Проверяем, была ли кнопка активная (default AppContext.positionActiveTags = 0)
            if (adapterPosition == AppContext.positionRcViewTags) {
                binding.textTags.setTextColor(Color.parseColor("#FFFFFF"))
                binding.cardViewTags.setCardBackgroundColor(Color.parseColor("#3364E0"))
            } else {
                // Делаем не активную кнопку
                binding.textTags.setTextColor(Color.parseColor("#FF000000"))
                binding.cardViewTags.setCardBackgroundColor(Color.parseColor("#f8f7f5"))
            }

            itemView.setOnClickListener {
                // Сохраняем позицию активной кнопки
                AppContext.positionRcViewTags = adapterPosition
                // Для реализации onClick во фрагменте
                interfaceTags.onClickTags(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTagsBinding.inflate(inflater, parent, false)
        return TagsHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TagsHolder, position: Int) {
        holder.bind(list[position], interfaceTags)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listItem: List<TagsModel>) {
        list.addAll(listItem)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update() {
        notifyDataSetChanged()
    }

    interface InterfaceTags {
        fun onClickTags(adapterPosition: Int)
    }
}