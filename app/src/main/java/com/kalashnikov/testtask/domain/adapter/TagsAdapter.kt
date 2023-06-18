package com.kalashnikov.testtask.domain.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.testtask.databinding.ItemTagsBinding
import com.kalashnikov.testtask.domain.management.Function
import com.kalashnikov.testtask.domain.management.AppContext
import com.kalashnikov.testtask.domain.usecase.RcViewTags

class TagsAdapter : RecyclerView.Adapter<TagsAdapter.TagsHolder>() {
    private val list = ArrayList<TagsData>()

    class TagsHolder(private val binding: ItemTagsBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(data: TagsData) = with(binding) {
            textTags.text = data.tags

            // Заменить на не активный тег
            notActiveTags(AppContext.oldIdTags)
            // Заменить на активный тег
            activeTags(AppContext.newIdTags)
        }

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            // Записываем новое значения как уже старое
            AppContext.oldIdTags = AppContext.newIdTags
            // Записываем новое значения для активной кнопки тега
            AppContext.newIdTags = adapterPosition
            // Сортируем по тегу и обновляем адаптер
            Function.tags(adapterPosition)
            // Обновляем адаптер для активного тега
            AppContext.tagsAdapter.updateAdapter(RcViewTags.execute())
        }

        private fun notActiveTags(idTags: Int) {
            if (adapterPosition == idTags) {
                binding.textTags.setTextColor(Color.parseColor("#FF000000"))
                binding.cardViewTags.setCardBackgroundColor(Color.parseColor("#f8f7f5"))
            }
        }

        private fun activeTags(idTags: Int) {
            if (adapterPosition == idTags) {
                binding.textTags.setTextColor(Color.parseColor("#FFFFFF"))
                binding.cardViewTags.setCardBackgroundColor(Color.parseColor("#3364E0"))
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
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listItem: List<TagsData>) {
        list.clear()
        list.addAll(listItem)
        notifyDataSetChanged()
    }
}