package com.kalashnikov.testtask.domain.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.testtask.databinding.ItemMainBinding
import com.kalashnikov.testtask.domain.management.AppContext

class MainAdapter(private val interfaceMain: InterfaceMain): RecyclerView.Adapter<MainAdapter.MainHolder>() {
    private val list = ArrayList<MainData>()

    class MainHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(mainData: MainData, interfaceMain: InterfaceMain) = with(binding) {
            imageMain.setImageResource(mainData.image)
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
    fun updateAdapter(listItem: List<MainData>) {
        list.addAll(listItem)
        notifyDataSetChanged()
    }

    interface InterfaceMain {
        fun onClickMain()
    }
}