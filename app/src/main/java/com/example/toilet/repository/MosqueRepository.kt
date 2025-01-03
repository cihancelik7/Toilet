package com.example.toilet.repository

import com.example.toilet.data.Place
import com.example.toilet.data.PlaceRepository
import com.google.firebase.firestore.FirebaseFirestore

class MosqueRepository: PlaceRepository() {
    private val firestore = FirebaseFirestore.getInstance()


    fun ayasofyaData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/ayasofyaMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun balcikfulyaMosq(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/balcikfulyaMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun beyazitData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/beyazitMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun ciftecevizlerMosq(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/ciftecevizlerMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun ferikoypasamahallesiMosq(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/ferikoypasamahallesiMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun fulyayeniMosq(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/fulyayeniMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun hacibulentvezirogluMosq(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/hacibulentvezirogluMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun harbiyeMosq(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/harbiyeMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun halilrifatmahallesiMosq(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/halilrifatmahallesiMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun hamidiyemesrutiyetMosq(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/hamidiyemesrutiyetMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun hasanzeynebMosq(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/hasanzeynebMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun izzetpasaMosq(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/izzetpasaMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun ihlamurMosq(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/ihlamurMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun muradiyeData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/muradiyeMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun nuruosmaniyeData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/nuruosmaniyeMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun suleymaniyeData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/suleymaniyeMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun sultanBayezitData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/sultanbayezitMosq" // Tesvikiye Camii için path
        getDataFromFirestore(collectionPath, callback)
    }
    fun sisliMosq(callback: (List<Place>) -> Unit){
        val colelctionPath = "places/mosque/sisliMosq"
        getDataFromFirestore(colelctionPath,callback)
    }
    fun sultanAhmetData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/sultanahmetMosq"
        getDataFromFirestore(collectionPath, callback)
    }
    fun talatpasamerkezMosq(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/talatpasamerkezMosq" // Tesvikiye Camii için path
        getDataFromFirestore(collectionPath, callback)
    }
    fun tesvikiyeData(callback: (List<Place>) -> Unit) {
        val collectionPath = "places/mosque/tesvikiyeMosq" // Tesvikiye Camii için path
        getDataFromFirestore(collectionPath, callback)
    }
    fun yeniData(callback: (List<Place>) -> Unit){
        val collectionPath = "places/mosque/yeniMosq"
        getDataFromFirestore(collectionPath,callback)
    }













}