package com.kalashnikov.testtask.presentation.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.kalashnikov.testtask.R
import com.kalashnikov.testtask.domain.management.Variables
import com.kalashnikov.testtask.presentation.mvvm.MainViewModel
import com.squareup.picasso.Picasso

@SuppressLint("StaticFieldLeak")
object CardDialog {
    lateinit var imageFavourites: ImageView
    lateinit var imageClose: ImageView
    lateinit var buttonAdd: Button

    @SuppressLint("SetTextI18n")
    fun initDialog(context: Context, position: Int) {

        Variables.dialog = Dialog(context)
        Variables.dialog.setContentView(R.layout.alert_dialog)
        Variables.dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Инициализация кнопок на экране диалога
        imageFavourites = Variables.dialog.findViewById<ImageView>(R.id.imageFavourites)
        imageClose = Variables.dialog.findViewById<ImageView>(R.id.imageClose)
        buttonAdd = Variables.dialog.findViewById<Button>(R.id.buttonAdd)

        val imageUrl = Variables.dialog.findViewById<ImageView>(R.id.imageUrl)
        val textName = Variables.dialog.findViewById<TextView>(R.id.textName)
        val textPrice = Variables.dialog.findViewById<TextView>(R.id.textPrice)
        val textWeight = Variables.dialog.findViewById<TextView>(R.id.textWeight)
        val textDescription = Variables.dialog.findViewById<TextView>(R.id.textDescription)

        Picasso.get().load(Variables.listAll[position].image_url).into(imageUrl)
        textName.text = Variables.listAll[position].name
        textPrice.text = "${Variables.listAll[position].price} ₽ "
        textWeight.text = "· ${Variables.listAll[position].weight}г"
        textDescription.text = Variables.listAll[position].description
    }

}