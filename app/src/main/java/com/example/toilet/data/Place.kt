package com.example.toilet.data

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val id: String, // Her yerin benzersiz ID'si
    val name: String,
    val location: LatLng,
    val type: PlaceType,
    val description: String,
    var averageRating: Double,
    val category: String, // Kategori bilgisi (örneğin: mosque, mall, metro)
    val subCategory: String // Alt kategori bilgisi (örneğin: tesvikiyeMosq, zorluMall)
) : Parcelable

enum class PlaceType {
    MOSQUE, METRO, MALL, CIHAN // Yeni türler eklendi
}