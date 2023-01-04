package com.example.currentlocation.data.remote

import com.example.currentlocation.model.Route
import com.google.android.gms.maps.model.LatLng
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
   private val directionsService: DirectionsService
) : RemoteDataSource {
    override fun getRoute(origin: LatLng, destination: LatLng): Single<Route> =
        directionsService.getRoute(
            "${origin.latitude},${origin.longitude}",
            "${destination.latitude},${destination.longitude}"
        )
}