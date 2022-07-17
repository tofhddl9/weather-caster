package com.lgtm.weathercaster.presentation.widgets.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.lgtm.weathercaster.R
import com.lgtm.weathercaster.domain.vo.WeatherDataVO
import com.lgtm.weathercaster.domain.vo.item.HourlyWeatherVO
import com.lgtm.weathercaster.domain.vo.item.WeatherItemVO
import com.lgtm.weathercaster.databinding.ItemHourlyWeatherBinding
import com.lgtm.weathercaster.databinding.ViewHourlyWeatherSummaryBinding
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType
import com.lgtm.weathercaster.utils.time.SystemTimeProvider
import com.lgtm.weathercaster.utils.time.TimeProvider
import com.lgtm.weathercaster.utils.time.hourToStringFormat
import kotlin.math.roundToInt

class HourlyWeatherSummaryVH(
    private val binding: ViewBinding,
) : WeatherVH(binding) {

    override val viewType: Int = WeatherViewType.HOURLY_WEATHER_SUMMARY

    override fun bindData(data: WeatherItemVO) = with(binding as ViewHourlyWeatherSummaryBinding) {
        data as HourlyWeatherVO

        title.text = data.title

        recyclerView.adapter = HourlyWeatherItemAdapter()
        (recyclerView.adapter as HourlyWeatherItemAdapter).setItems(data.hourlyWeathers)
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
    }

}

private class HourlyWeatherItemAdapter : RecyclerView.Adapter<HourlyWeatherItemVH>() {

    private var items: List<WeatherDataVO> = emptyList()

    fun setItems(data: List<WeatherDataVO>?) {
        data ?: return

        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherItemVH {
        return HourlyWeatherItemVH(ItemHourlyWeatherBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HourlyWeatherItemVH, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int = items.size

}

private class HourlyWeatherItemVH(
    private val binding: ViewBinding,
    private val timeProvider: TimeProvider = SystemTimeProvider()
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: WeatherDataVO) = with(binding as ItemHourlyWeatherBinding) {
        val dt = data.dt

        hour.text = hourToStringFormat(timeProvider.getHour(dt))
        temperature.text = "${data.temperature.roundToInt()}\u00B0"
        Glide.with(weatherIcon)
            .load(data.weatherMetaData?.icon)
            .placeholder(R.drawable.common_google_signin_btn_icon_dark)
            .into(weatherIcon)
    }

}
