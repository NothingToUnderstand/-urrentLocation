package com.example.currentlocation


import android.os.Bundle
import com.example.currentlocation.databinding.ActivityMapsBinding
import com.example.currentlocation.locationFragment.LocationFragment


class MapsActivity : BaseActivity() {

    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transaction(LocationFragment())
    }
}