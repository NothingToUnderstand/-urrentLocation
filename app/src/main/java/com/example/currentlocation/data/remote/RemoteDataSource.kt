package com.example.currentlocation.data.remote

import com.example.currentlocation.model.Route
import com.google.android.gms.maps.model.LatLng
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun getRoute(origin:LatLng,destination:LatLng):Single<Route>
}