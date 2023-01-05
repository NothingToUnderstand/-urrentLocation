package com.example.currentlocation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.currentlocation.di.LocationComponent

abstract class BaseActivity : AppCompatActivity() {
    lateinit var locationComponent: LocationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        locationComponent = (applicationContext as MapsApp)
            .appComponent.locationComponent().create()
        locationComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
    protected fun transaction(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}