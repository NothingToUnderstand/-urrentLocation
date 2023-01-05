package com.example.currentlocation.locationFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currentlocation.data.remote.RemoteDataSource
import com.example.currentlocation.di.ActivityScope
import com.example.currentlocation.model.RouteX
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class GeoViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {

    val data = MutableLiveData<List<List<LatLng>>>()

    fun getData(origin: LatLng, destination: LatLng) {
        remoteDataSource.getRoute(origin, destination)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    getSteps(it.routes)
                },
                {
                    Log.e("DataRouteError", "Error data: $data")
                }
            )
    }

    private fun getSteps(routes: List<RouteX>) {
        val list = mutableListOf<List<LatLng>>()
        routes.forEach { route ->
            route.legs.forEach { leg ->
                leg.steps.forEach { step ->
                    list.add(PolyUtil.decode(step.polyline.points))
                }
            }
        }
        data.postValue(list)
    }
}
