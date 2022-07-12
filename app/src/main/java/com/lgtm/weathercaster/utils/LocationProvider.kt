package com.lgtm.weathercaster.utils

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import java.util.*
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

interface LocationProvider {
    suspend fun getCurrentLocation(): Location?
    fun getAddress(location: Location): String?
}

@ExperimentalCoroutinesApi
class LocationProviderImpl @Inject constructor(
    private val application: Application,
    private val locationClient: FusedLocationProviderClient
) : LocationProvider {

    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!hasAccessFineLocationPermission || !hasAccessCoarseLocationPermission || !isGpsEnabled) {
            return null
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cont.resume(result, null)
                    } else {
                        cont.resume(null, null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it, null)
                }
                addOnFailureListener {
                    cont.resume(null, null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

    override fun getAddress(location: Location): String {
        val position = LatLng(location.latitude, location.longitude)
        val geoCoder = Geocoder(application, Locale.getDefault())
        val address = geoCoder.getFromLocation(position.latitude, position.longitude, 1).getOrNull(0)

        return "${address?.subLocality ?: ""} ${address?.thoroughfare ?: ""}"
    }

}
