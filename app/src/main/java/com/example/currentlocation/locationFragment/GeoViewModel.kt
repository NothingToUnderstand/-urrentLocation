package com.example.currentlocation.locationFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currentlocation.data.remote.RemoteDataSource
import com.example.currentlocation.di.ActivityScope
import com.example.currentlocation.model.Route
import com.google.android.gms.maps.model.LatLng
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class GeoViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {
    val data = MutableLiveData<Route>()
    fun getData(origin: LatLng, destination: LatLng): MutableLiveData<Route> {

        remoteDataSource.getRoute(origin, destination)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    data.postValue(it)
                },
                {
                    Log.e("DataRouteError", "Error data: $data")
                }
            )
        return data
    }
}