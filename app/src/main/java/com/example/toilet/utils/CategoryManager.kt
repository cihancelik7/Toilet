package com.example.toilet.utils

class CategoryManager {
    private val categoryMap = mapOf(
        "mosque" to listOf(
            "muradiyeMosq", "tesvikiyeMosq", "sultanbayezitMosq",
            "ayasofyaMosq", "beyazitMosq", "nuruosmaniyeMosq",
            "suleymaniyeMosq", "sultanahmetMosq", "yeniMosq"
        ),
        "mall" to listOf(
            "zorluMall", "citysMall", "istinyeParkMall", "akasyaMall",
            "aquafloryaMall", "capacityMall", "cevahirMall", "emaarsquareMall",
            "kanyonMall", "mallofistanbulMall", "marmaraforumMall", "ozdilekparkMall",
            "palldiumMall", "tepanautilusMall", "trumpMall", "vadiistanbulMall", "watergardenMall"
        ),
        "metro" to listOf(
            "4leventMetro","arnavutkoyhastaneMetro", "aksarayMetro", "atakoyMetro", "ataturkhavalimaniMetro",
            "ataturkotosanayiMetro", "bahcelievlerMetro", "bakirkoyMetro", "bayrampasaMetro",
            "darussafakaMetro", "davutpasaMetro", "dtmMetro", "emniyetMetro", "esenlerMetro",
            "gayrettepeMetro","gokturkMetro", "haciosmanMetro", "halicMetro","ihsaniyeMetro","istanbulhavalimaniMetro", "ituayazagaMetro","kagithaneMetro", "kocatepeMetro",
            "leventMetro", "mecidiyekoyMetro", "merterMetro", "osmanbeyMetro", "sagmalcilarMetro",
            "sanayimahallesiMetro", "seyrantepeMetro", "sishaneMetro", "taksimMetro", "terazidereMetro",
            "topkapiUlubatliMetro", "veznecilerMetro", "yenibosnaMetro", "yenikapiMetro", "zeytinburnuMetro"
        )
        // "gebzeMetro", "daricaMetro" "osmangaziMetro", "gebzeteknikuniMetro" , "cayirovaMetro", "tuzlaMetro", "icmelerMetro", "aydintepeMetro", "guzelyaliMetro", "tersaneMetro","kaynarcaMetro", "pendikMetro
    )

    fun getSubCategories(category: String): List<String>? {
        return categoryMap[category]
    }

    fun isValidCategory(category: String, subCategory: String): Boolean {
        return categoryMap[category]?.contains(subCategory) == true
    }

    // Yeni Metod
    fun getSubCategoryById(category: String, placeId: String): String? {
        return categoryMap[category]?.find { it == placeId }
    }
}