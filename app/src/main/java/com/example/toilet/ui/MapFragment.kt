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
import com.example.toilet.R
import com.example.toilet.data.Place
import com.example.toilet.data.PlaceType
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private val markers = mutableListOf<Marker>()  // Marker'ları tutacak liste

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SupportMapFragment'i başlat
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragmentContainer) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun onZoomLevelChanged(zoomLevel: Float) {
        // Marker'ların boyutunu zoom seviyesine göre güncelle
        markers.forEach { marker ->
            val newIcon = getMarkerIcon(zoomLevel, marker.tag as PlaceType)  // her marker için doğru ikon tipi
            marker.setIcon(newIcon)  // Marker'ın ikonunu güncelle
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Zoom seviyesi değiştiğinde marker'ları güncelle
        googleMap.setOnCameraIdleListener {
            val zoomLevel = googleMap.cameraPosition.zoom
            onZoomLevelChanged(zoomLevel)  // Zoom seviyesini alıp marker'ları güncelle
        }

        val uiSettings = googleMap.uiSettings
        uiSettings.isZoomControlsEnabled = true  // Yakınlaştırma ve uzaklaştırma butonlarını aktif et

        val places = listOf(
            Place("Cami Tuvaleti", LatLng(41.0082, 28.9784), PlaceType.MOSQUE),
            Place("Metro Tuvaleti", LatLng(41.0050, 28.9750), PlaceType.METRO),
            Place("AVM Tuvaleti", LatLng(41.0100, 28.9800), PlaceType.MALL)
        )

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }

        // Markerları eklerken zoom'a göre ikonları seç
        places.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .position(place.location)
                    .title(place.name)
                    .icon(getMarkerIcon(googleMap.cameraPosition.zoom, place.type))  // Zoom seviyesine göre ikon
            )
            marker?.let {
                it.tag = place.type  // Marker'ın tag'ine yer tipini ekle
                markers.add(it)  // Marker'ı listeye ekle
            }
        }

        val sampleLocation = LatLng(41.05680681802559, 28.97963929921389) // İstanbul koordinatları
        googleMap.addMarker(
            MarkerOptions()
                .position(sampleLocation)
                .title("Big Kaka Area!!")
                .icon(getMarkerIcon(googleMap.cameraPosition.tilt, PlaceType.CIHAN)) // Zoom'a göre ikon al
        )

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sampleLocation, 14f))
    }

    private fun updateMarkerIcons(zoomLevel: Float) {
        // Marker'ların boyutunu zoom seviyesine göre güncelle
        markers.forEach { marker ->
            marker.setIcon(getMarkerIcon(zoomLevel, PlaceType.MOSQUE))  // Örneğin cami ikonu
        }
    }

    private fun getMarkerIcon(zoomLevel: Float, placeType: PlaceType): BitmapDescriptor {
        val size = when {
            zoomLevel < 40 -> 80  // çok uzak
            zoomLevel < 45 -> 105  // orta mesafe
            else -> 100  // yakın
        }

        // Her placeType için uygun ikonu seç
        val iconResource = when (placeType) {
            PlaceType.MOSQUE -> R.drawable.mosque // Cami ikonu
            PlaceType.METRO -> R.drawable.subway  // Metro ikonu
            PlaceType.MALL -> R.drawable.mall    // AVM ikonu
            else -> R.drawable.cihan           // Varsayılan ikon
        }

        return BitmapDescriptorFactory.fromBitmap(resizeMapIcons(iconResource, size))
    }

    private fun resizeMapIcons(iconResourceId: Int, size: Int): Bitmap {
        val image = BitmapFactory.decodeResource(resources, iconResourceId)
        return Bitmap.createScaledBitmap(image, size, size, false)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                googleMap.isMyLocationEnabled = true
            }
        }
    }
}