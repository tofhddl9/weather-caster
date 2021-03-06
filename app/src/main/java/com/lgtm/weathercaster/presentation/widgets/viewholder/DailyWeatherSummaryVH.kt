package com.lgtm.weathercaster.presentation.widgets.viewholder

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.lgtm.weathercaster.R
import com.lgtm.weathercaster.domain.vo.WeatherDataVO
import com.lgtm.weathercaster.domain.vo.item.DailyWeatherVO
import com.lgtm.weathercaster.domain.vo.item.WeatherItemVO
import com.lgtm.weathercaster.databinding.ItemDailyWeatherBinding
import com.lgtm.weathercaster.databinding.ViewDailyWeatherSummaryBinding
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType
import com.lgtm.weathercaster.utils.time.SystemTimeProvider
import com.lgtm.weathercaster.utils.time.TimeProvider
import com.lgtm.weathercaster.utils.time.toKorean
import kotlin.math.max
import kotlin.math.roundToInt

class DailyWeatherSummaryVH(
    private val binding: ViewBinding,
) : WeatherVH(binding) {

    override val viewType: Int = WeatherViewType.DAILY_WEATHER_SUMMARY

    override fun bindData(data: WeatherItemVO) = with(binding as ViewDailyWeatherSummaryBinding) {
        data as DailyWeatherVO

        title.text = data.title

        recyclerView.adapter = DailyWeatherItemAdapter()
        (recyclerView.adapter as DailyWeatherItemAdapter).setItems(data.dailyWeathers)
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
    }

}

private class DailyWeatherItemAdapter : RecyclerView.Adapter<DailyWeatherItemVH>() {

    private var items: List<WeatherDataVO> = emptyList()

    private var minTemp = 0f

    private var maxTemp = 0f

    fun setItems(data: List<WeatherDataVO>?) {
        data ?: return

        minTemp = data.minOf { it.temperatureMin ?: 0f }
        maxTemp = data.maxOf { it.temperatureMax ?: 0f }

        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherItemVH {
        return DailyWeatherItemVH(ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DailyWeatherItemVH, position: Int) {
        holder.bindData(items[position], minTemp, maxTemp)
    }

    override fun getItemCount(): Int = items.size

}

private class DailyWeatherItemVH(
    private val binding: ViewBinding,
    private val timeProvider: TimeProvider = SystemTimeProvider()
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: WeatherDataVO, minTemp: Float, maxTemp: Float) = with(binding as ItemDailyWeatherBinding) {
        val dt = data.dt
        if (timeProvider.isToday(dt)) {
            day.text =  "??????"
            day.typeface = Typeface.DEFAULT_BOLD
        } else {
            day.text = "${timeProvider.getMonth(dt)}/${timeProvider.getDayOfMonth(dt)} (${timeProvider.getDayWeek(dt).toKorean()})"
            day.typeface = Typeface.DEFAULT
        }

        minTemperature.text = "${data.temperatureMin?.roundToInt()}\u00B0"
        maxTemperature.text = "${data.temperatureMax?.roundToInt()}\u00B0"

        temperatureBar.setData(
            minTemp.roundToInt(),
            maxTemp.roundToInt(),
            data.temperatureMin?.roundToInt() ?: minTemp.roundToInt(),
            data.temperatureMax?.roundToInt() ?: maxTemp.roundToInt()
        )

        Glide.with(weatherIcon)
            .load(data.weatherMetaData?.icon)
            .placeholder(R.drawable.common_google_signin_btn_icon_dark)
            .into(weatherIcon)
    }

}