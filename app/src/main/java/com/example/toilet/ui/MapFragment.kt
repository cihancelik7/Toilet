package com.example.toilet.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.toilet.R
import com.example.toilet.data.Place
import com.example.toilet.data.PlaceType
import com.example.toilet.viewmodel.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var viewModel: MapViewModel
    private val markers = mutableListOf<Marker>()
    private val pendingMarkers = mutableListOf<Place>() // Harita hazır olana kadar biriken markerlar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MapViewModel::class.java)

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragmentContainer) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Veriyi yükle
        viewModel.loadMosqueData()
        viewModel.loadMetroData()
        viewModel.loadMallData()

        // Mosque verisini gözlemle
        viewModel.mosquePlaces.observe(viewLifecycleOwner) { places ->
            processPlaces(places)
        }

        // Metro verisini gözlemle
        viewModel.metroPlaces.observe(viewLifecycleOwner) { places ->
            processPlaces(places)
        }

        // Mall verisini gözlemle
        viewModel.mallPlaces.observe(viewLifecycleOwner) { places ->
            processPlaces(places)
        }

    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        Log.d("MapFragment", "Harita başarıyla yüklendi.")
        pendingMarkers.forEach { addMarker(it) }
        pendingMarkers.clear()
        googleMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireContext(),
                R.raw.map_style
            )
        )
        googleMap.setOnInfoWindowClickListener { marker ->
            val place = marker.tag as? Place // Place nesnesini marker ile ilişkilendiriyoruz
            place?.let {
                val detailsFragment = DetailsFragment.newInstance(it)
                detailsFragment.show(parentFragmentManager, "DetailsFragment")
            }
        }
        googleMap.setOnCameraIdleListener {
            val zoomLevel = googleMap.cameraPosition.zoom
            updateMarkersByZoom(zoomLevel)
        }

        // İstanbul koordinatları
        val istanbul = LatLng(41.0082, 28.9784)

        // İstanbul'a odaklanmak ve harita zoom seviyesini ayarlamak
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(istanbul, 12f))


        // Zoom butonlarını aktif et
        googleMap.uiSettings.isZoomControlsEnabled = true

        googleMap.setOnCameraIdleListener {
            val zoomLevel = googleMap.cameraPosition.zoom
            updateMarkersByZoom(zoomLevel)
        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }
    }
    private fun processPlaces(places: List<Place>) {
        if (!::googleMap.isInitialized) {
            pendingMarkers.addAll(places)
        } else {
            places.forEach { addMarker(it) }
        }
    }


    private fun addMarker(place: Place) {
        val markerOptions = MarkerOptions()
            .position(place.location)
            .title("${place.name} - ${place.rating}")
            .snippet(place.description)
            .icon(getMarkerIcon(place.type, googleMap.cameraPosition.zoom))

        val marker = googleMap.addMarker(markerOptions)
        marker?.tag = place // Marker ile Place ilişkilendirildi
        marker?.let { markers.add(it) }
    }


    private fun updateMarkersByZoom(zoomLevel: Float) {
        markers.forEach { marker ->
            val placeType = marker.tag as? PlaceType
            placeType?.let {
                marker.setIcon(getMarkerIcon(it, zoomLevel))
            }
        }
    }
    // Marker ikonu için dinamik boyut ve görsel ayarlaması
    private fun getMarkerIcon(placeType: PlaceType, zoomLevel: Float): BitmapDescriptor {
        val size = when {
            zoomLevel <= 40 -> 90  // En yakın zoom seviyesi
            zoomLevel <= 45 -> 95
            else -> 110  // En uzak zoom seviyesi
        }

        val iconResource = when (placeType) {
            PlaceType.MOSQUE -> R.drawable.mosque
            PlaceType.METRO -> R.drawable.subway
            PlaceType.MALL -> R.drawable.mall
            else -> R.drawable.cihan  // Default görsel
        }

        val originalIcon = BitmapFactory.decodeResource(resources, iconResource)
        val resizedIcon = Bitmap.createScaledBitmap(originalIcon, size, size, false)
        return BitmapDescriptorFactory.fromBitmap(resizedIcon)
    }
}