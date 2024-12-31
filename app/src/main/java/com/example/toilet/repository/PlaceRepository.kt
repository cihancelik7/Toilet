package com.example.toilet.data

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore

open class PlaceRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun getDataFromFirestore(
        collectionPath: String,
        callback: (List<Place>) -> Unit
    ) {
        firestore.collection(collectionPath)
            .get()
            .addOnSuccessListener { documents ->
                val placesList = mutableListOf<Place>()
                for (document in documents) {
                    try {
                        val id = document.id
                        val name = document.getString("name") ?: "Bilinmiyor"
                        val geoPoint = document.getGeoPoint("location")
                        val latLng = geoPoint?.let { LatLng(it.latitude, it.longitude) } ?: LatLng(0.0, 0.0)
                        val type = document.getString("type") ?: "UNKNOWN"
                        val description = document.getString("description") ?: "Açıklama yok"
                        val rating = document.getDouble("averageRating") ?: 0.0
                        val category = document.getString("category") ?: "Unknown"

                        val placeType = when (type.uppercase()) {
                            "MOSQUE" -> PlaceType.MOSQUE
                            "METRO" -> PlaceType.METRO
                            "MALL" -> PlaceType.MALL
                            else -> PlaceType.CIHAN
                        }

                        val place = Place(id, name, latLng, placeType, description, rating, category, "")
                        placesList.add(place)
                    } catch (e: Exception) {
                        Log.e("FirestoreError", "Veri hatası: ${document.id}, Hata: ${e.message}")
                    }
                }
                callback(placesList)
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Veri çekme hatası: ${exception.message}")
            }
    }
}