package com.kalashnikov.testtask.domain.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.kalashnikov.testtask.R
import com.kalashnikov.testtask.databinding.ItemMainBinding
import com.kalashnikov.testtask.domain.management.AppContext
import com.kalashnikov.testtask.domain.usecase.GetMain
import com.kalashnikov.testtask.presentation.fragment.CategoriesFragment
import com.squareup.picasso.Picasso

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainHolder>() {
    private val list = ArrayList<MainData>()

    class MainHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(data: MainData) = with(binding) {
            textName.text = data.name
            Picasso.get().load(data.image_url).into(imageMain)
        }

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            // Записываем название категории
            AppContext.mvvm.loadTextCategories(GetMain.model.сategories[adapterPosition].name)

            // Открываем фрагмент
            (v.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fLayout, CategoriesFragment.newInstance())
                .commit()
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
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listItem: List<MainData>) {
        list.clear()
        list.addAll(listItem)
        notifyDataSetChanged()
    }
}