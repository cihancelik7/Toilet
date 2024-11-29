package com.example.toilet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.toilet.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Uygulama başladığında MapFragment'i yükle
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MapFragment()) // Doğru ID kullanımı
                .commit() // İşlemi tamamla
        }
    }
}