package com.example.toilet.repository

import android.util.Log
import com.example.toilet.data.Place
import com.example.toilet.data.PlaceRepository
import com.example.toilet.data.PlaceType
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore

class MosqueRepository: PlaceRepository() {
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
    fun getAyasofyaData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/ayasofyaMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun getBeyazitData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/beyazitMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun getNuruosmaniyeData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/nuruosmaniyeMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun getSuleymaniyeData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/suleymaniyeMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun getSultanAhmetData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/sultanahmetMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun getYeniData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/yeniMosq"
        getDataFromFirestore(collectionPath,callback)
    }













}