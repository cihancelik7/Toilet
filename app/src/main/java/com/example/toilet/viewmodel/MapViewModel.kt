package com.example.toilet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toilet.data.Place
import com.example.toilet.repository.MallRepository
import com.example.toilet.repository.MetroRepository
import com.example.toilet.repository.MosqueRepository

class MapViewModel : ViewModel() {

    private val mosqueRepository = MosqueRepository()
    private val metroRepository = MetroRepository()
    private val mallRepository = MallRepository()

    private val _mosquePlaces = MutableLiveData<List<Place>>()
    val mosquePlaces: LiveData<List<Place>> get() = _mosquePlaces

    private val _metroPlaces = MutableLiveData<List<Place>>()
    val metroPlaces: LiveData<List<Place>> get() = _metroPlaces

    private val _mallPlaces = MutableLiveData<List<Place>>()
    val mallPlaces: LiveData<List<Place>> get() = _mallPlaces

    // Rating güncellemesi için LiveData
    private val _updatedRating = MutableLiveData<Double>()
    val updatedRating: LiveData<Double> get() = _updatedRating

    // Güncelleme için LiveData
    private val _updatedPlace = MutableLiveData<Place>()
    val updatedPlace: LiveData<Place> get() = _updatedPlace



    fun loadMosqueData() {
        mosqueRepository.getMuradiyeData { placesList ->
            _mosquePlaces.value = placesList
        }
        mosqueRepository.getTesvikiyeData { placesList ->
            _mosquePlaces.value = placesList
        }
        mosqueRepository.getSultanBayezitData { placeList ->
            _mosquePlaces.value = placeList
        }
        mosqueRepository.getAyasofyaData { placeList ->
            _mosquePlaces.value = placeList
        }
        mosqueRepository.getBeyazitData { placeList ->
            _mosquePlaces.value = placeList
        }
        mosqueRepository.getNuruosmaniyeData { placeList ->
            _mosquePlaces.value = placeList
        }
        mosqueRepository.getSuleymaniyeData { placeList ->
            _mosquePlaces.value = placeList
        }
        mosqueRepository.getSultanAhmetData { placeList ->
            _mosquePlaces.value = placeList
        }
        mosqueRepository.getYeniData { placeList ->
            _mosquePlaces.value = placeList
        }
    }

    fun loadMetroData() {
        metroRepository.get4LeventMetro { placesList ->
            _metroPlaces.value = placesList
        }
        metroRepository.arnavutkoyhastaneMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.aksarayMetroData{placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.atakoyMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.ataturkHavalimaniMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.ataturkOtoSanayiMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.aydintepeMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.bahcelievlerMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.bakirkoyMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.bayrampasaMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.cayirovaMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.darussafakaMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.daricaMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.davutpasaMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.dtmMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.emniyetMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.esenlerMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.gayrettepeMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.gebzeMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.gebzeteknikuniMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.gokturkMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.guzelyaliMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.haciosmanMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.hasdalMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.halicMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.icmelerMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.ihsaniyeMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.istanbulhavalimaniMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.ituAyazagaMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.kagithaneMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.kaynarcaMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.kemerburgazMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.kocatepeMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.leventMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.mecidiyekoyMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.merterMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.getOsmanbeyMetroData { placesList ->
            _metroPlaces.value = placesList
        }
        metroRepository.osmangaziMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.pendikMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.sagmalcilarMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.sanayiMahallesiMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.seyratepeMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.getSishaneMetroData { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.getTaksimMetroData { placesList ->
            _metroPlaces.value = placesList
        }
        metroRepository.terazidereMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.tersaneMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.topkapiUlubatliMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.tuzlaMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.veznecilerMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.yenibosnaMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.yenikapiMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.zeytinburnuMetro { placeList->
            _metroPlaces.value = placeList
        }
    }

    fun loadMallData() {
        mallRepository.getZorluMallData { placesList ->
            _mallPlaces.value = placesList
        }
        mallRepository.getCitysMallData { placesList ->
            _mallPlaces.value = placesList
        }
        mallRepository.getIstinyeMallData { placesList ->
            _mallPlaces.value = placesList
        }
        mallRepository.getAkasyaMallData { placeList->
            _mallPlaces.value = placeList
        }
        mallRepository.getAquafloryaMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getcapacityMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getCevahirMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getEmaarsquareMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getKanyonMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getMallofIstanbulMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getMarmaraForumMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getOzdilekparkMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getPalldiumMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.gettepenautilusMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getTrumpMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getVadiIstanbulMall { placeList ->
            _mallPlaces.value = placeList
        }
        mallRepository.getWatergardenMall { placeList ->
            _mallPlaces.value = placeList
        }
    }

    // Rating güncellemelerini takip et
    fun updateRating(updatedPlace: Place) {
        _updatedPlace.value = updatedPlace
    }
}