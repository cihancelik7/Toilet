package com.example.toilet.utils

import android.view.View
import com.example.toilet.data.Place
import com.example.toilet.data.PlaceType
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

object DetailsHelper {
    fun configureIcons(place: Place, restroomIcon: View, disabledToiletIcon: View, babyIcon: View) {
        val description = place.description.lowercase()
        if ("erkek" in description || "kadın" in description) {
            restroomIcon.visibility = View.VISIBLE
        }
        if ("engelli" in description) {
            disabledToiletIcon.visibility = View.VISIBLE
        }
        if ("bebek bakım" in description) {
            babyIcon.visibility = View.VISIBLE
        }
    }

    fun getDocumentReference(
        firestore: FirebaseFirestore,
        place: Place,
        categoryManager: CategoryManager
    ): DocumentReference? {
        val category = when (place.type) {
            PlaceType.MOSQUE -> "mosque"
            PlaceType.MALL -> "mall"
            PlaceType.METRO -> "metro"
            else -> return null
        }

        val subCategory = categoryManager.getSubCategories(category)?.find { it.contains(place.id) }
            ?: return null

        return firestore.collection("places")
            .document(category)
            .collection(subCategory)
            .document(place.id)
    }
}