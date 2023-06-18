package com.kalashnikov.testtask.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kalashnikov.testtask.R
import com.kalashnikov.testtask.databinding.ActivityMainBinding
import com.kalashnikov.testtask.domain.management.Variables
import com.kalashnikov.testtask.presentation.fragment.BasketFragment
import com.kalashnikov.testtask.presentation.fragment.MainFragment
import com.kalashnikov.testtask.presentation.mvvm.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Variables.mvvm = ViewModelProvider(this)[MainViewModel::class.java]

        // При открытии app активируется кнопка Главная (Нижняя навигационная панель)
        binding.bNav.selectedItemId = R.id.home

        // При открытии app первым открывается экран Главная (Fragment)
        supportFragmentManager.beginTransaction().replace(R.id.fLayout, MainFragment.newInstance())
            .commit()

        initButtonNavigation()
    }

    private fun initButtonNavigation() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fLayout, MainFragment.newInstance()).commit()
                }
                R.id.search -> {}
                R.id.bag -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fLayout, BasketFragment.newInstance()).commit()
                }
                R.id.profile -> {}
            }
            true
        }
    }
}