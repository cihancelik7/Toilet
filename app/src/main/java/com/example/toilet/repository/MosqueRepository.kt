package com.example.toilet.data

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore

class MosqueRepository {
    private val firestore = FirebaseFirestore.getInstance()



    fun getMuradiyeData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/muradiyeMosq" // Muradiye Camii için path
        getDataFromFirestore(collectionPath, callback)
    }

    fun getTesvikiyeData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/tesvikiyeMosq" // Tesvikiye Camii için path
        getDataFromFirestore(collectionPath, callback)
    }

    fun getSultanBayezitData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/sultanbayezitMosq" // Tesvikiye Camii için path
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