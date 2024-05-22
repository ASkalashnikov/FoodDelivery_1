package com.kalashnikov.tt_fooddelivery_1.domain.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.tt_fooddelivery_1.databinding.ItemMainBinding
import com.kalashnikov.tt_fooddelivery_1.domain.management.AppContext
import com.kalashnikov.tt_fooddelivery_1.domain.model.MainModel

class MainAdapter(private val interfaceMain: InterfaceMain): RecyclerView.Adapter<MainAdapter.MainHolder>() {
    private val list = ArrayList<MainModel>()

    class MainHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(mainModel: MainModel, interfaceMain: InterfaceMain) = with(binding) {
            imageMain.setImageResource(mainModel.image)
            //Picasso.get().load(data.image_url).into(imageBasket)

            itemView.setOnClickListener {
                // Сохраняем позицию
                AppContext.positionRcViewMain = adapterPosition
                // Для реализации onClick во фрагменте
                interfaceMain.onClickMain()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(inflater, parent, false)
        return MainHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(list[position], interfaceMain)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listItem: List<MainModel>) {
        list.addAll(listItem)
        notifyDataSetChanged()
    }

    interface InterfaceMain {
        fun onClickMain()
    }
}