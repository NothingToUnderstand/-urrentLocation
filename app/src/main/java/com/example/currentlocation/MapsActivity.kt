package com.example.currentlocation


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.currentlocation.databinding.ActivityMapsBinding
import com.example.currentlocation.di.LocationComponent
import com.example.currentlocation.di.MapsApp
import com.example.currentlocation.locationFragment.LocationFragment


class MapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding
    lateinit var locationComponent: LocationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        locationComponent = (applicationContext as MapsApp)
            .appComponent.locationComponent().create()
        locationComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transaction(LocationFragment())
    }

    private fun transaction(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}