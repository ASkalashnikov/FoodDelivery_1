package com.kalashnikov.tt_fooddelivery_1.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kalashnikov.tt_fooddelivery_1.R
import com.kalashnikov.tt_fooddelivery_1.databinding.ActivityMainBinding
import com.kalashnikov.tt_fooddelivery_1.presentation.fragment.BasketFragment
import com.kalashnikov.tt_fooddelivery_1.presentation.fragment.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // При открытии app выделяется кнопка "Главная" - Нижняя навигационная панель
        binding.bNav.selectedItemId = R.id.home

        // При открытии app открывается MainFragment
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