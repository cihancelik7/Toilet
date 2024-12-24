package com.example.toilet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.toilet.R
import com.example.toilet.data.Place
import com.example.toilet.data.PlaceType
import com.example.toilet.viewmodel.MapViewModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class DetailsFragment : DialogFragment() {

    private lateinit var place: Place
    private lateinit var viewModel: MapViewModel

    companion object {
        private const val ARG_PLACE = "arg_place"

        fun newInstance(place: Place): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable(ARG_PLACE, place)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.TransparentDialog)
        place = arguments?.getParcelable(ARG_PLACE)
            ?: throw IllegalArgumentException("Place is required")
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        // ViewModel'i activity'den al
        viewModel = ViewModelProvider(requireActivity()).get(MapViewModel::class.java)

        // UI Elemanları
        val tvPlaceName = view.findViewById<TextView>(R.id.tvPlaceName)
        val rbRating = view.findViewById<RatingBar>(R.id.rbRating)
        val ratingValue = view.findViewById<TextView>(R.id.tvAverageRating)
        val ivRestroom = view.findViewById<ImageView>(R.id.ivRestroom)
        val ivToilet = view.findViewById<ImageView>(R.id.ivToilet)
        val ivBaby = view.findViewById<ImageView>(R.id.ivBaby)

        // Verilerin Yüklenmesi
        tvPlaceName.text = place.name
        rbRating.rating = place.averageRating.toFloat()
        viewModel.updatedRating.observe(viewLifecycleOwner) { updatedRating ->
            // Ekranda güncellenmiş ortalama puanı yansıt
            val ratingValue = view.findViewById<TextView>(R.id.tvAverageRating)
            ratingValue.text = "Average Rating: ${String.format("%.1f", updatedRating)}"
        }

        // Özellik Kontrolü
        val description = place.description
        if ("Erkek" in description || "Kadın" in description) {
            ivRestroom.visibility = View.VISIBLE
        }
        if ("Engelli" in description) {
            ivToilet.visibility = View.VISIBLE
        }
        if ("Bebek Bakım" in description) {
            ivBaby.visibility = View.VISIBLE
        }

        // Kullanıcı Puanlama
        setupRatingSystem(view)

        return view
    }

    private fun setupRatingSystem(view: View) {
        val rbRating = view.findViewById<RatingBar>(R.id.rbRating)
        val btnSubmitRating = view.findViewById<TextView>(R.id.tvRate) // Puan Ver butonu
        var selectedRating: Float = 0.0f

        // RatingBar'da değişiklik olduğunda
        rbRating.setOnRatingBarChangeListener { _, rating, _ ->
            selectedRating = rating
            btnSubmitRating.visibility = View.VISIBLE
        }

        // Puan Ver butonuna basıldığında
        btnSubmitRating.setOnClickListener {
            if (selectedRating > 0) {
                btnSubmitRating.isEnabled = false
                sendRatingToFirebase(selectedRating) { success, message ->
                    btnSubmitRating.isEnabled = true
                    if (success) {
                        Toast.makeText(context, "Puanınız kaydedildi!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Hata: $message", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Lütfen bir puan seçin.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendRatingToFirebase(rating: Float, callback: (Boolean, String?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()

        // Kategori ve alt kategori bilgilerini alın
        val category = when (place.type) {
            PlaceType.MOSQUE -> "mosque"
            PlaceType.MALL -> "mall"
            PlaceType.METRO -> "metro"
            else -> null
        } ?: run {
            callback(false, "Geçersiz kategori.")
            return
        }

        val subCategory = when (category) {
            "mosque" -> "tesvikiyeMosq" // Burası dinamik olmalı
            "mall" -> "akasyaMall"
            "metro" -> "taksimMetro"
            else -> null
        } ?: run {
            callback(false, "Geçersiz alt kategori.")
            return
        }

        // Path: /places/{category}/{subCategory}/{autoUID}
        val documentRef = firestore.collection("places")
            .document(category)
            .collection(subCategory)
            .document(place.id)

        // `ratings` alt koleksiyonuna yeni bir puan ekle
        val ratingsCollectionRef = documentRef.collection("ratings")
        ratingsCollectionRef.add(mapOf("rating" to rating))
            .addOnSuccessListener {
                updateAverageRating(documentRef) { success, message ->
                    callback(success, message)
                }
            }
            .addOnFailureListener { e ->
                callback(false, e.message)
            }
    }

    private fun updateAverageRating(documentRef: DocumentReference, callback: (Boolean, String?) -> Unit) {
        // `ratings` alt koleksiyonundaki tüm puanları al
        documentRef.collection("ratings").get()
            .addOnSuccessListener { querySnapshot ->
                val ratings = querySnapshot.documents.mapNotNull { it.getDouble("rating") }
                val averageRating = if (ratings.isNotEmpty()) ratings.average() else 0.0

                // Ortalama puanı ana belgeye güncelle
                documentRef.update("averageRating", averageRating)
                    .addOnSuccessListener {
                        // ViewModel'e güncellenmiş rating'i gönder
                        viewModel.updateRating(averageRating)
                        callback(true, null)
                    }
                    .addOnFailureListener { e ->
                        callback(false, e.message)
                    }
            }
            .addOnFailureListener { e ->
                callback(false, e.message)
            }
    }
}