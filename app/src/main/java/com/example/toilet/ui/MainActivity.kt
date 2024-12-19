package com.example.toilet.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.toilet.R
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Uygulama başladığında MapFragment'i yükle
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MapFragment())
                .commit()
        }

        // Firebase bağlantısını mevcut koleksiyon için test et
        testFirebaseWithExistingCollection()
    }

    private fun testFirebaseWithExistingCollection() {
        // Halihazırda var olan koleksiyona bağlanın
        val collectionPath = "places/mall/zorluMall"

        FirebaseFirestore.getInstance().collection(collectionPath)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Log.d("FirebaseTest", "Bağlantı başarılı, ancak veri bulunamadı.")
                } else {
                    documents.forEach { document ->
                        Log.d("FirebaseTest", "Veri: ${document.id} => ${document.data}")
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirebaseTest", "Bağlantı hatası: ${exception.message}")
            }
    }
}