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
            "ataturkotosanayiMetro","aydintepeMetro", "bahcelievlerMetro", "bakirkoyMetro", "bayrampasaMetro","cayirovaMetro","daricaMetro",
            "darussafakaMetro", "davutpasaMetro", "dtmMetro", "emniyetMetro", "esenlerMetro",
            "gayrettepeMetro","gebzeMetro","gebzeteknikuniMetro", "gokturkMetro","guzelyaliMetro", "haciosmanMetro", "halicMetro","icmelerMetro", "ihsaniyeMetro","istanbulhavalimaniMetro",
            "ituayazagaMetro","kagithaneMetro", "kaynarcaMetro", "kocatepeMetro",
            "leventMetro", "mecidiyekoyMetro", "merterMetro", "osmanbeyMetro","osmangaziMetro","pendikMetro", "sagmalcilarMetro",
            "sanayimahallesiMetro", "seyrantepeMetro", "sishaneMetro", "taksimMetro", "terazidereMetro", "tersaneMetro",
            "topkapiUlubatliMetro","tuzlaMetro", "veznecilerMetro", "yenibosnaMetro", "yenikapiMetro", "zeytinburnuMetro"
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