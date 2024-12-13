//package com.example.toilet.data
//
//import com.google.android.gms.maps.model.LatLng
//import com.google.firebase.firestore.FirebaseFirestore
//
//class PlaceRepository {
//
//    private val firestore = FirebaseFirestore.getInstance()
//
//    // Genelleştirilmiş veri çekme fonksiyonu
//    fun getPlaceData(collectionPath: String, callback: (List<Place>) -> Unit) {
//        firestore.collection(collectionPath)
//            .get()
//            .addOnSuccessListener { documents ->
//                val placesList = mutableListOf<Place>()
//                for (document in documents) {
//                    val id = document.id // Firestore'dan gelen benzersiz ID
//                    val name = document.getString("name") ?: "Bilinmiyor"
//                    val geoPoint = document.getGeoPoint("location")
//                    val latLng = geoPoint?.let { LatLng(it.latitude, it.longitude) } ?: LatLng(0.0, 0.0)
//                    val type = document.getString("type") ?: "MOSQUE"
//                    val description = document.getString("description") ?: "Açıklama yok"
//                    val rating = document.getDouble("rating") ?: 0.0
//                    val category = document.getString("category") ?: "Unknown" // Kategori bilgisi
//
//                    val placeType = when (type.uppercase()) {
//                        "MOSQUE" -> PlaceType.MOSQUE
//                        "METRO" -> PlaceType.METRO
//                        "MALL" -> PlaceType.MALL
//                        else -> PlaceType.CIHAN
//                    }
//
//                    val place = Place(id, name, latLng, placeType, description, rating, category)
//                    placesList.add(place)
//                }
//                callback(placesList)
//            }
//            .addOnFailureListener { exception ->
//                // Hata işlemi
//            }
//    }
//}