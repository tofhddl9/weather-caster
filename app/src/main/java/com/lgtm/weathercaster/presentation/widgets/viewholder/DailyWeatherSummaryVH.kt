package com.lgtm.weathercaster.presentation.widgets.viewholder

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.lgtm.weathercaster.R
import com.lgtm.weathercaster.data.vo.WeatherDataVO
import com.lgtm.weathercaster.data.vo.item.DailyWeatherVO
import com.lgtm.weathercaster.data.vo.item.WeatherItemVO
import com.lgtm.weathercaster.databinding.ItemDailyWeatherBinding
import com.lgtm.weathercaster.databinding.ViewDailyWeatherSummaryBinding
import com.lgtm.weathercaster.presentation.widgets.WeatherViewType
import com.lgtm.weathercaster.utils.TimeProvider
import com.lgtm.weathercaster.utils.toKorean
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

    fun setItems(data: List<WeatherDataVO>?) {
        data ?: return

        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherItemVH {
        return DailyWeatherItemVH(ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DailyWeatherItemVH, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int = items.size

}

private class DailyWeatherItemVH(
    private val binding: ViewBinding,
    private val timeProvider: TimeProvider = TimeProvider()
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: WeatherDataVO) = with(binding as ItemDailyWeatherBinding) {
        val dt = data.dt*1000L
        if (timeProvider.isToday(dt)) {
            day.text =  "오늘"
            day.typeface = Typeface.DEFAULT_BOLD
        } else {
            day.text = "${timeProvider.getMonth(dt)}/${timeProvider.getDayOfMonth(dt)} (${timeProvider.getDayWeek(dt).toKorean()})"
            day.typeface = Typeface.DEFAULT
        }

        minTemperature.text = "${data.temperatureMin?.roundToInt()}"
        maxTemperature.text = "${data.temperatureMax?.roundToInt()}"
        Glide.with(weatherIcon)
            .load(data.weatherMetaData?.icon)
            .placeholder(R.drawable.common_google_signin_btn_icon_dark)
            .into(weatherIcon)
    }

}