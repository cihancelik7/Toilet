package com.example.toilet.repository

import android.util.Log
import com.example.toilet.data.Place
import com.example.toilet.data.PlaceType
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore

class MallRepository {
    private val firestore = FirebaseFirestore.getInstance()

    fun getMallData(subCategory: String, callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mall/$subCategory" // Dinamik olarak subCategory'yi kullanıyoruz
        getDataFromFirestore(collectionPath, callback)
    }

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
    fun getAkasyaMallData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/akasyaMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getAquafloryaMall(callback: (List<Place>) -> Unit){
        val colelctionPath = "places/mall/aquafloryaMall"
        getDataFromFirestore(colelctionPath,callback)
    }
    fun getcapacityMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/capacityMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getCevahirMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/cevahirMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getEmaarsquareMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/emaarsquareMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getKanyonMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/kanyonMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getMallofIstanbulMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/mallofistanbulMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getMarmaraForumMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/marmaraforumMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getOzdilekparkMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/ozdilekparkMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getPalldiumMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/palldiumMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun gettepenautilusMall(callback: (List<Place>) -> Unit){
        val collectionPath= "places/mall/tepanautilusMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getTrumpMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/trumpMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getVadiIstanbulMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/vadiistanbulMall"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getWatergardenMall(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mall/watergardenMall"
        getDataFromFirestore(collectionPath,callback)
    }



    fun getDataFromFirestore(collectionPath: String, callback: (List<Place>) -> Unit) {
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
                        val rating = document.getDouble("rating") ?: 0.0
                        val category = document.getString("category") ?: "Unknown"

                        val placeType = when (type.uppercase()) {
                            "MOSQUE" -> PlaceType.MOSQUE
                            "METRO" -> PlaceType.METRO
                            "MALL" -> PlaceType.MALL
                            else -> PlaceType.CIHAN
                        }

                        val place = Place(id, name, latLng, placeType, description, rating, category, subCategory = "")
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