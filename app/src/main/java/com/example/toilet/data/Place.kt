package com.example.toilet.data

import com.google.android.gms.maps.model.LatLng

data class Place(
    val id: String, // Her yerin benzersiz ID'si
    val name: String,
    val location: LatLng,
    val type: PlaceType,
    val description: String,
    val rating: Double,
    val category: String // (Opsiyonel) Kategori bilgisi
)

enum class PlaceType {
    MOSQUE, METRO, MALL, CIHAN // Yeni türler eklendi
}