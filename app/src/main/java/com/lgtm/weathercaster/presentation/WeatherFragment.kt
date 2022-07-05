package com.lgtm.weathercaster.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment: Fragment() {

    private val viewModel: WeatherViewModel by viewModels()

}