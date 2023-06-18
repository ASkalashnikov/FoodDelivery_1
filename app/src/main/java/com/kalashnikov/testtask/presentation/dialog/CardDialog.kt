package com.kalashnikov.testtask.presentation.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.kalashnikov.testtask.R
import com.kalashnikov.testtask.domain.management.AppContext
import com.squareup.picasso.Picasso

@SuppressLint("StaticFieldLeak")
object CardDialog {
    lateinit var imageFavourites: ImageView
    lateinit var imageClose: ImageView
    lateinit var buttonAdd: Button

    @SuppressLint("SetTextI18n")
    fun initDialog(context: Context, position: Int) {

        AppContext.dialog = Dialog(context)
        AppContext.dialog.setContentView(R.layout.alert_dialog)
        AppContext.dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Инициализация кнопок на экране диалога
        imageFavourites = AppContext.dialog.findViewById<ImageView>(R.id.imageFavourites)
        imageClose = AppContext.dialog.findViewById<ImageView>(R.id.imageClose)
        buttonAdd = AppContext.dialog.findViewById<Button>(R.id.buttonAdd)

        val imageUrl = AppContext.dialog.findViewById<ImageView>(R.id.imageUrl)
        val textName = AppContext.dialog.findViewById<TextView>(R.id.textName)
        val textPrice = AppContext.dialog.findViewById<TextView>(R.id.textPrice)
        val textWeight = AppContext.dialog.findViewById<TextView>(R.id.textWeight)
        val textDescription = AppContext.dialog.findViewById<TextView>(R.id.textDescription)

        Picasso.get().load(AppContext.listAll[position].image_url).into(imageUrl)
        textName.text = AppContext.listAll[position].name
        textPrice.text = "${AppContext.listAll[position].price} ₽ "
        textWeight.text = "· ${AppContext.listAll[position].weight}г"
        textDescription.text = AppContext.listAll[position].description
    }

}