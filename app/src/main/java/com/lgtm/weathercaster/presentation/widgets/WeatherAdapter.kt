package com.lgtm.weathercaster.presentation.widgets

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lgtm.weathercaster.data.vo.item.WeatherItemVO
import com.lgtm.weathercaster.databinding.ViewDailyWeatherSummaryBinding
import com.lgtm.weathercaster.databinding.ViewWeatherSummaryBinding
import com.lgtm.weathercaster.presentation.widgets.viewholder.WeatherVH
import com.lgtm.weathercaster.presentation.widgets.viewholder.CurrentWeatherSummaryVH
import com.lgtm.weathercaster.presentation.widgets.viewholder.DailyWeatherSummaryVH
import com.lgtm.weathercaster.presentation.widgets.viewholder.HourlyWeatherSummaryVH

class WeatherAdapter : ListAdapter<WeatherItemVO, WeatherVH>(WeatherDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherVH {
        return when (viewType) {
            WeatherViewType.CURRENT_WEATHER_SUMMARY -> {
                CurrentWeatherSummaryVH(ViewWeatherSummaryBinding.inflate(LayoutInflater.from(parent.context)))
            }
            WeatherViewType.DAILY_WEATHER_SUMMARY -> {
                DailyWeatherSummaryVH(ViewDailyWeatherSummaryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            WeatherViewType.HOURLY_WEATHER_SUMMARY -> {
                HourlyWeatherSummaryVH(ViewWeatherSummaryBinding.inflate(LayoutInflater.from(parent.context)))
            }
            else -> {
                CurrentWeatherSummaryVH(ViewWeatherSummaryBinding.inflate(LayoutInflater.from(parent.context)))
            }
        }
    }

    override fun onBindViewHolder(holder: WeatherVH, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemId
    }
}


class WeatherDiffCallback : DiffUtil.ItemCallback<WeatherItemVO>() {

    override fun areItemsTheSame(oldItem: WeatherItemVO, newItem: WeatherItemVO): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: WeatherItemVO, newItem: WeatherItemVO): Boolean {
        return oldItem == newItem
    }

}
