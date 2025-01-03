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
        ratingValue.text = "Average Rating: ${String.format("%.1f", place.averageRating)}"

        viewModel.updatedPlace.observe(viewLifecycleOwner) { updatedPlace ->
            if (updatedPlace.id == place.id) {
                // Yıldız değerini güncelle
                rbRating.rating = updatedPlace.averageRating.toFloat()

                // Average Rating metnini güncelle
                ratingValue.text = "Average Rating: ${String.format("%.1f", updatedPlace.averageRating)}"
            }
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

        val subCategory = getSubCategory(category, place.name) ?: run {
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
        documentRef.collection("ratings").get()
            .addOnSuccessListener { querySnapshot ->
                val ratings = querySnapshot.documents.mapNotNull { it.getDouble("rating") }
                val averageRating = if (ratings.isNotEmpty()) ratings.average() else 0.0

                // Ortalama puanı ana belgeye güncelle
                documentRef.update("averageRating", averageRating)
                    .addOnSuccessListener {
                        // Güncellenen Place'i ViewModel'e ilet
                        val updatedPlace = place.copy(averageRating = averageRating)
                        viewModel.updateRating(updatedPlace)
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

    private fun getSubCategory(category: String, placeName: String): String? {
        val subCategories = when (category) {
            "mosque" -> mapOf(
                "Ayasofya Mosque" to "ayasofyaMosq",
                "Beyazıt Camii" to "beyazitMosq",
                "Balçık Fulya Cami" to "balcikfulyaMosq",
                "Çiftecevizler Cami" to "ciftecevizlerMosq",
                "Feriköy Paşa Mahallesi Cami" to "ferikoypasamahallesiMosq",
                "Fulya Yeni Cami" to "fulyayeniMosq",
                "Hacı Bülent Veziroğlu Cami" to "hacibulentvezirogluMosq",
                "Harbiye Cami" to "harbiyeMosq",
                "Halil Rıfat Mahallesi Cami" to "halilrifatmahallesiMosq",
                "Hamidiye Meşrutiyet Cami" to "hamidiyemesrutiyetMosq",
                "Hasan Zeyneb Cami" to "hasanzeynebMosq",
                "Ihlamur Cami" to "ihlamurMosq",
                "İzzet Paşa Cami" to "izzetpasaMosq",
                "Muradiye Camii" to "muradiyeMosq",
                "Nuruosmaniye Camii" to "nuruosmaniyeMosq",
                "Sultan Beyazidi Veli Cami" to "sultanbayezitMosq",
                "Şişli Camii" to "sisliMosq",
                "Süleymaniye Camii" to "suleymaniyeMosq",
                "Sultanahmet Camii" to "sultanahmetMosq",
                "Talatpaşa Merkez Cami" to "talatpasamerkezMosq",
                "Teşvikiye Camii" to "tesvikiyeMosq",
                "Yeni Camii" to "yeniMosq"
            )
            "mall" -> mapOf(
                "Zorlu Center" to "zorluMall",
                "City's Nişantaşı" to "citysMall",
                "İstinye Park" to "istinyeParkMall",
                "Akasya Mall" to "akasyaMall",
                "Aqua Florya" to "aquafloryaMall",
                "Capacity Mall" to "capacityMall",
                "Cevahir Mall" to "cevahirMall",
                "Emaar Square Mall" to "emaarsquareMall",
                "Kanyon Mall" to "kanyonMall",
                "Mall of Istanbul" to "mallofistanbulMall",
                "Marmara Forum" to "marmaraforumMall",
                "Özdilek Park Mall" to "ozdilekparkMall",
                "Palladium AVM" to "palldiumMall",
                "Tepe Nautilus" to "tepanautilusMall",
                "Trump Mall" to "trumpMall",
                "Vadi Istanbul" to "vadiistanbulMall",
                "Watergarden" to "watergardenMall"
            )
            "metro" -> mapOf(
                "4. Levent Metro" to "4leventMetro",
                "Arnavutköy Hastane Metro" to "arnavutkoyhastaneMetro",
                "Aksaray Metro" to "aksarayMetro",
                "Ataköy Şirinevler Metro" to "atakoyMetro",
                "Atatürk Havalimanı Metro" to "ataturkhavalimaniMetro",
                "Atatürk Oto Sanayi Metro" to "ataturkotosanayiMetro",
                "Aydıntepe Metro" to "aydintepeMetro",
                "Bahçelievler Metro" to "bahcelievlerMetro",
                "Bakırköy Metro" to "bakirkoyMetro",
                "Bayrampaşa Metro" to "bayrampasaMetro",
                "Çayırova Metro" to "cayirovaMetro",
                "Darüşşafaka Metro" to "darussafakaMetro",
                "Darıca Metro" to "daricaMetro",
                "Davutpaşa Metro" to "davutpasaMetro",
                "Dünya Ticaret Merkezi Metro" to "dtmMetro",
                "Emniyet Metro" to "emniyetMetro",
                "Esenler Metro" to "esenlerMetro",
                "Gayrettepe Metro" to "gayrettepeMetro",
                "Gebze Metro" to "gebzeMetro",
                "Gebze Teknik Üniversitesi Metro" to "gebzeteknikuniMetro",
                "Göktürk Metro" to "gokturkMetro",
                "Güzelyalı Metro" to "guzelyaliMetro",
                "Hasdal Metro" to "hasdalMetro",
                "Hacıosman Metro" to "haciosmanMetro",
                "Haliç Metro" to "halicMetro",
                "Itü-Ayazağa Metro" to "ituayazagaMetro",
                "İçmeler Metro" to "icmelerMetro",
                "Istanbul Havalimanı Metro" to "istanbulhavalimaniMetro",
                "Ihsaniye Metro" to "ihsaniyeMetro",
                "Kemerburgaz Metro" to "kemerburgazMetro",
                "Kağıthane Metro" to "kagithaneMetro",
                "Kaynarca Metro" to "kaynarcaMetro",
                "Kocatepe Metro" to "kocatepeMetro",
                "Levent Metro" to "leventMetro",
                "Mecidiyeköy Metro" to "mecidiyekoyMetro",
                "Merter Metro" to "merterMetro",
                "Osmanbey Metro" to "osmanbeyMetro",
                "Osmangazi Metro" to "osmangaziMetro",
                "Pendik Metro" to "pendikMetro",
                "Sağmalcılar Metro" to "sagmalcilarMetro",
                "Sanayi Mahallesi Metro" to "sanayimahallesiMetro",
                "Seyrantepe Metro" to "seyrantepeMetro",
                "Şişhane Metro" to "sishaneMetro",
                "Taksim Metro Durağı" to "taksimMetro",
                "Terazidere Metro" to "terazidereMetro",
                "Tersane Metro" to "tersaneMetro",
                "Topkapı - Ulubatlı Metro" to "topkapiUlubatliMetro",
                "Tuzla Metro" to "tuzlaMetro",
                "Vezneciler Metro" to "veznecilerMetro",
                "Yenibosna Metro" to "yenibosnaMetro",
                "Yenikapı Metro" to "yenikapiMetro",
                "Zeytinburnu Metro" to "zeytinburnuMetro"
            )
            else -> null
        }
        return subCategories?.get(placeName)
    }
}