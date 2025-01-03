package com.example.toilet.utils

class CategoryManager {
    private val categoryMap = mapOf(
        "mosque" to listOf(

             "ayasofyaMosq","balcikfulyaMosq", "beyazitMosq","beyogluaksemsettinMosq" ,"caglayanyeniMosq" ,"cengiztopelMosq" ,"ciftecevizlerMosq", "darulacezeMosq" ,"ferikoypasamahallesiMosq" ,"fulyayeniMosq",
            "hacibulentvezirogluMosq" ,"hacitevfikzadeMosq" ,"hamidiyemesrutiyetMosq" ,"halilrifatmahallesiMosq", "harbiyeMosq" ,"hasanzeynebMosq" ,"huzurMosq" ,"ihlamurMosq","izzetpasaMosq" ,
            "kustepeMosq" ,"muradiyeMosq","nuruosmaniyeMosq","sisliMosq", "suleymaniyeMosq", "sultanahmetMosq",
            "sultanbayezitMosq","talatpasamerkezMosq" ,"tesvikiyeMosq", "yeniMosq"
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
        // "kustepeMosq" , "hacitevfikzadeMosq" , "darulacezeMosq" , "caglayanyeniMosq" , "cengiztopelMosq" , "huzurMosq" , "beyogluaksemsettinMosq" , "" , "" , "" , "" , "" , ""
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