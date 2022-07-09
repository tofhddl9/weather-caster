package com.lgtm.weathercaster.presentation

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.lgtm.weathercaster.R
import com.lgtm.weathercaster.databinding.FragmentWeatherBinding
import com.lgtm.weathercaster.presentation.widgets.WeatherAdapter
import com.lgtm.weathercaster.utils.delegate.viewBinding
import com.lgtm.weathercaster.utils.permission.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment: Fragment(R.layout.fragment_weather) {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private val binding: FragmentWeatherBinding by viewBinding(FragmentWeatherBinding::bind)

    private val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foo()

        initViews()

        observeViewModel()
    }

    private fun foo() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.getCurrentWeather()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
    }

    private fun initViews() {
        val weatherAdapter = WeatherAdapter()
        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle).collect {
                weatherAdapter.submitList(it.weatherUiData)
            }
        }

        with(binding.recyclerView) {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                }
            }
        }
    }

}