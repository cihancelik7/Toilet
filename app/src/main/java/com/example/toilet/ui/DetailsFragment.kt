package com.example.toilet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.toilet.R
import com.example.toilet.data.Place
import com.google.firebase.firestore.FirebaseFirestore

class DetailsFragment : DialogFragment() {

    private lateinit var place: Place // Place nesnesi

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

        // UI Güncellemesi
        val tvPlaceName = view.findViewById<TextView>(R.id.tvPlaceName)
      //  val tvPlaceDescription = view.findViewById<TextView>(R.id.tvPlaceDescription)
        val rbRating = view.findViewById<RatingBar>(R.id.rbRating)
        val ratingValue = view.findViewById<TextView>(R.id.tvAverageRating)
        val ivPlaceImage = view.findViewById<ImageView>(R.id.ivPlaceImage)

        tvPlaceName.text = place.name
       // tvPlaceDescription.text = place.description
        rbRating.rating = place.rating.toFloat()
        ratingValue.text = "Average Rating: "+place.rating.toString()




        val ivRestroom = view.findViewById<ImageView>(R.id.ivRestroom)
        val ivToilet = view.findViewById<ImageView>(R.id.ivToilet)
        val ivBaby = view.findViewById<ImageView>(R.id.ivBaby)


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



        // Firebase'den dinamik olarak fotoğraf URL'sini çekmek için örnek kod (Glide kullanılabilir)
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("places")
            .document(place.id)
            .get()
            .addOnSuccessListener { document ->
                val imageUrl = document.getString("imageUrl") // Belgedeki `imageUrl` alanını kontrol et
                if (!imageUrl.isNullOrEmpty()) {
                    // Glide ile yükleme (isteğe bağlı)
                    /*
                    Glide.with(this)
                        .load(imageUrl) // Fotoğraf URL'si
                        .placeholder(R.drawable.placeholder_image) // Yüklenirken gösterilecek görsel
                        .into(ivPlaceImage)
                    */
                } else {
                 //   ivPlaceImage.setImageResource(R.drawable.placeholder_image) // Varsayılan görsel
                }
            }
            .addOnFailureListener { e ->
             //   ivPlaceImage.setImageResource(R.drawable.error_image) // Hata durumunda görsel
                e.message?.let { android.util.Log.e("DetailsFragment", it) }
            }

        return view
    }
}