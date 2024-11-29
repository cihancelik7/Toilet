package com.example.toilet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toilet.data.Place
import com.example.toilet.data.PlaceType
import com.google.android.gms.maps.model.LatLng

class MapViewModel: ViewModel() {

    // yerlerin listesi ve bunlarin marker verileri
    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> get() = _places

    init {
        loadPlaces()
    }

    private fun loadPlaces() {
        // veriyi yukle (ornegin: sabit veriler
        val loadPlaces= listOf(
            Place("Cami Tuvaleti", LatLng(41.0082, 28.9784), PlaceType.MOSQUE),
            Place("Metro Tuvaleti", LatLng(41.0050, 28.9750), PlaceType.METRO),
            Place("AVM Tuvaleti", LatLng(41.0100, 28.9800), PlaceType.MALL)
        )
        _places.value = loadPlaces


    }

}