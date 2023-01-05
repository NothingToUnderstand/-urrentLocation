package com.example.currentlocation

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.currentlocation.locationFragment.GeoViewModel
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModel: GeoViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MapsActivity).locationComponent.inject(this)
    }
}