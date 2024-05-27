package com.kalashnikov.tt_fooddelivery_1.presentation.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
        // Получения разрешение на местоположение
        getLocationPermission()
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

    private fun getLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            ActivityCompat.requestPermissions(this, permissions, 0)
        }
    }
}