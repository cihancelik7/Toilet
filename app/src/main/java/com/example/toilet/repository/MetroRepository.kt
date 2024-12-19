package com.example.toilet.repository

import android.util.Log
import com.example.toilet.data.Place
import com.example.toilet.data.PlaceType
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore

class MetroRepository {
    private val firestore = FirebaseFirestore.getInstance()

    fun getOsmanbeyMetroData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/metro/osmanbeyMetro" // Osmanbey Metro için path
        getDataFromFirestore(collectionPath, callback)
    }

    fun getTaksimMetroData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/metro/taksimMetro" // Taksim Metro için path
        getDataFromFirestore(collectionPath, callback)
    }
    fun getSishaneMetroData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/metro/sishaneMetro" // Taksim Metro için path
        getDataFromFirestore(collectionPath, callback)
    }
    fun aksarayMetroData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/aksarayMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun atakoyMetro(callback: (List<Place>) -> Unit){
        val collectionPath= "places/metro/atakoyMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun ataturkHavalimaniMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/ataturkhavalimaniMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun bahcelievlerMetro(callback: (List<Place>) -> Unit){
        val colelctionPath = "places/metro/bahcelievlerMetro"
        getDataFromFirestore(colelctionPath,callback)
    }
    fun bakirkoyMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/bayrampasaMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun davutpasaMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/davutpasaMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun dtmMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/dtmMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun emniyetMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/emniyetMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun esenlerMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/esenlerMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun kocatepeMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/kocatepeMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun merterMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/merterMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun sagmalcilarMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/sagmalcilarMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun terazidereMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/terazidereMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun topkapiUlubatliMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/topkapiUlubatliMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun yenibosnaMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/yenibosnaMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun yenikapiMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/yenikapiMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun zeytinburnuMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/zeytinburnuMetro"
        getDataFromFirestore(collectionPath,callback)
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