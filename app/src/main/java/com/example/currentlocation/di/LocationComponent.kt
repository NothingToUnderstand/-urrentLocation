package com.example.currentlocation.di

import com.example.currentlocation.MapsActivity
import com.example.currentlocation.locationFragment.LocationFragment
import dagger.Subcomponent
import javax.inject.Scope

@ActivityScope
@Subcomponent
interface LocationComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LocationComponent
    }

    fun inject(activity: MapsActivity)
    fun inject(fragment: LocationFragment)
}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope
