package com.example.toilet.data

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.PlaceTypes

data class Place(
    val name: String,
    val location: LatLng,
    val type: PlaceType
)

enum class PlaceType{
    MOSQUE, METRO, MALL, CIHAN
}
