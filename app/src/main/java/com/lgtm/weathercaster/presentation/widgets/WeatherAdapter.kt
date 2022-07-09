package com.lgtm.weathercaster.presentation.widgets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lgtm.weathercaster.data.WeatherVO
import com.lgtm.weathercaster.databinding.ViewWeatherSummaryBinding
import com.lgtm.weathercaster.presentation.WeatherUiState
import com.lgtm.weathercaster.presentation.widgets.viewholder.WeatherVH
import com.lgtm.weathercaster.presentation.widgets.viewholder.WeatherSummaryVH

class ViewHolderCreator

class WeatherAdapter : ListAdapter<WeatherVO, WeatherVH>(WeatherDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherVH {
        return when (viewType) {
            WeatherViewType.WEATHER_VIEW_TYPE_1 -> {
                WeatherSummaryVH(ViewWeatherSummaryBinding.inflate(LayoutInflater.from(parent.context)))
            }
            WeatherViewType.WEATHER_VIEW_TYPE_2 -> {
                WeatherSummaryVH(ViewWeatherSummaryBinding.inflate(LayoutInflater.from(parent.context)))
            }
            else -> {
                WeatherSummaryVH(ViewWeatherSummaryBinding.inflate(LayoutInflater.from(parent.context)))
            }
        }
    }

    override fun onBindViewHolder(holder: WeatherVH, position: Int) {
        holder.bindData(getItem(position))
    }
}


class WeatherDiffCallback : DiffUtil.ItemCallback<WeatherVO>() {

    override fun areItemsTheSame(oldItem: WeatherVO, newItem: WeatherVO): Boolean {
        return oldItem.current == newItem.current
    }

    override fun areContentsTheSame(oldItem: WeatherVO, newItem: WeatherVO): Boolean {
        return oldItem == newItem
    }

}