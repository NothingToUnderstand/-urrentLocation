package com.example.currentlocation

import android.app.Application
import com.example.currentlocation.di.ApplicationComponent
import com.example.currentlocation.di.DaggerApplicationComponent

class MapsApp : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}