package com.kalashnikov.testtask.presentation.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.kalashnikov.testtask.R
import com.kalashnikov.testtask.domain.adapter.BasketData
import com.kalashnikov.testtask.domain.adapter.CategoriesData
import com.kalashnikov.testtask.domain.management.AppContext

@SuppressLint("StaticFieldLeak")
class DialogMenu {
    private lateinit var dialog: Dialog
    private lateinit var imageFavourites: ImageView
    private lateinit var imageClose: ImageView
    private lateinit var buttonAdd: Button

    @SuppressLint("SetTextI18n")
    fun init(context: Context, data: CategoriesData) {

        dialog = Dialog(context)
        dialog.setContentView(R.layout.alert_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Инициализация кнопок на экране диалога
        imageFavourites = dialog.findViewById(R.id.imageFavourites)
        imageClose = dialog.findViewById(R.id.imageClose)
        buttonAdd = dialog.findViewById(R.id.buttonAdd)

        // Инициализация view на экране диалога
        val image = dialog.findViewById<ImageView>(R.id.imageUrl)
        val textName = dialog.findViewById<TextView>(R.id.textName)
        val textPrice = dialog.findViewById<TextView>(R.id.textPrice)
        val textWeight = dialog.findViewById<TextView>(R.id.textWeight)
        val textDescription = dialog.findViewById<TextView>(R.id.textDescription)

        textName.text = data.name
        textPrice.text = "${data.price} ₽ "
        textWeight.text = "· ${data.weight}г"
        image.setImageResource(data.image)
        textDescription.text = data.description

        // Кнопка, добавить в избранное
        var switch = false
        imageFavourites.setOnClickListener {
            switch = if (switch) {
                imageFavourites.setImageResource(R.drawable.favourites_delete)
                false
            } else {
                imageFavourites.setImageResource(R.drawable.favorite_add)
                true
            }
        }

        // Кнопка, закрыть диалог
        imageClose.setOnClickListener {
            dialog.cancel()
        }

        // Кнопка, добавляем продукт в корзину
        buttonAdd.setOnClickListener {
            val basketData = BasketData(
                id = data.id,
                name = data.name,
                price = data.price,
                weight = data.weight,
                image = data.image,
                quantity = 1
            )
            AppContext.basket.add(basketData)

            // Кнопку делаем не активной
            buttonAdd.alpha = 0.5f
            buttonAdd.isClickable = false

            // Выводим сообщение
            Toast.makeText(context, "Добавлено в корзину", Toast.LENGTH_SHORT).show()
        }

        // Проверяем было ли добавлено в корзину, если да то делаем кнопу не активной
        for (i in AppContext.basket.indices) {
            if (AppContext.basket[i].id == data.id) {
                buttonAdd.alpha = 0.5f
                buttonAdd.isClickable = false
                break
            }
        }
        dialog.show()
    }
}