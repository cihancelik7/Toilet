package com.example.toilet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toilet.data.MosqueRepository
import com.example.toilet.data.Place
import com.example.toilet.repository.MallRepository
import com.example.toilet.repository.MetroRepository

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
    }

    fun loadMetroData() {
        metroRepository.getOsmanbeyMetroData { placesList ->
            _metroPlaces.value = placesList
        }
        metroRepository.getTaksimMetroData { placesList ->
            _metroPlaces.value = placesList
        }
        metroRepository.getSishaneMetroData { placeList ->
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
        metroRepository.bahcelievlerMetro { placeList ->
            _metroPlaces.value = placeList
        }
        metroRepository.bakirkoyMetro { placeList ->
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
        metroRepository.kocatepeMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.merterMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.sagmalcilarMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.terazidereMetro { placeList->
            _metroPlaces.value = placeList
        }
        metroRepository.topkapiUlubatliMetro { placeList->
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
}