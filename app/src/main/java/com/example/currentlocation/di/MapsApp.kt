package com.example.currentlocation.di

import android.app.Application

class MapsApp : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}