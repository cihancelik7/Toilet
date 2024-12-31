package com.example.toilet.repository

import android.util.Log
import com.example.toilet.data.Place
import com.example.toilet.data.PlaceRepository
import com.example.toilet.data.PlaceType
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore

class MetroRepository:PlaceRepository() {
    private val firestore = FirebaseFirestore.getInstance()

    fun get4LeventMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/4leventMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun arnavutkoyhastaneMetro(callback: (List<Place>) -> Unit){
        val collectionPath ="places/metro/arnavutkoyhastaneMetro"
        getDataFromFirestore(collectionPath,callback)
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
    fun ataturkOtoSanayiMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/ataturkotosanayiMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun bahcelievlerMetro(callback: (List<Place>) -> Unit){
        val colelctionPath = "places/metro/bahcelievlerMetro"
        getDataFromFirestore(colelctionPath,callback)
    }
    fun bakirkoyMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/bakirkoyMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun bayrampasaMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/bayrampasaMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun darussafakaMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/darussafakaMetro"
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
    fun gayrettepeMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/gayrettepeMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun gokturkMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/gokturkMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun haciosmanMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/haciosmanMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun halicMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/halicMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun hasdalMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "/places/metro/hasdalMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun ihsaniyeMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "/places/metro/ihsaniyeMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun istanbulhavalimaniMetro(callback: (List<Place>) -> Unit){
        val collectionPath="places/metro/istanbulhavalimaniMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun ituAyazagaMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/ituayazagaMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun kagithaneMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/kagithaneMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun kemerburgazMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/kemerburgazMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun kocatepeMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/kocatepeMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun leventMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/leventMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun mecidiyekoyMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/mecidiyekoyMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun merterMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/merterMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getOsmanbeyMetroData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/metro/osmanbeyMetro" // Osmanbey Metro için path
        getDataFromFirestore(collectionPath, callback)
    }

    fun sagmalcilarMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/sagmalcilarMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun sanayiMahallesiMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/sanayimahallesiMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun seyratepeMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/seyrantepeMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun getSishaneMetroData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/metro/sishaneMetro" // Taksim Metro için path
        getDataFromFirestore(collectionPath, callback)
    }
    fun getTaksimMetroData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/metro/taksimMetro" // Taksim Metro için path
        getDataFromFirestore(collectionPath, callback)
    }

    fun terazidereMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/terazidereMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun topkapiUlubatliMetro(callback: (List<Place>) -> Unit){
        val collectionPath = "places/metro/topkapiUlubatliMetro"
        getDataFromFirestore(collectionPath,callback)
    }
    fun veznecilerMetro(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/metro/veznecilerMetro" // Taksim Metro için path
        getDataFromFirestore(collectionPath, callback)
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




}