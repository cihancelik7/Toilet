package com.example.toilet.repository

import android.util.Log
import com.example.toilet.data.Place
import com.example.toilet.data.PlaceType
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore

class MallRepository {
    private val firestore = FirebaseFirestore.getInstance()


    fun getZorluMallData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mall/zorluMall" // Zorlu Mall için path
        getDataFromFirestore(collectionPath, callback)
    }

    fun getCitysMallData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mall/citysMall" // Kanyon Mall için path
        getDataFromFirestore(collectionPath, callback)
    }
    fun getIstinyeMallData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mall/istinyeParkMall" // Kanyon Mall için path
        getDataFromFirestore(collectionPath, callback)
    }


    fun getDataFromFirestore(collectionPath: String, callback: (List<Place>) -> Unit) {
        firestore.collection(collectionPath) // Path dinamik olarak verilmiştir
            .get()
            .addOnSuccessListener { documents ->
                val placesList = mutableListOf<Place>()
                for (document in documents) {
                    val id = document.id
                    val name = document.getString("name") ?: "Bilinmiyor"
                    val geoPoint = document.getGeoPoint("location")
                    val latLng = geoPoint?.let { LatLng(it.latitude, it.longitude) } ?: LatLng(0.0, 0.0)
                    val type = document.getString("type") ?: "MOSQUE"
                    val description = document.getString("description") ?: "Açıklama yok"
                    val rating = document.getDouble("rating") ?: 0.0
                    val category = document.getString("category") ?: "Unknown"

                    val placeType = when (type.uppercase()) {
                        "MOSQUE" -> PlaceType.MOSQUE
                        "METRO" -> PlaceType.METRO
                        "MALL" -> PlaceType.MALL
                        else -> PlaceType.CIHAN
                    }

                    val place = Place(id, name, latLng, placeType, description, rating, category)
                    placesList.add(place)
                }
                callback(placesList)
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Veri çekme hatası: ${exception.message}")
            }
    }
}