package com.lgtm.weathercaster.presentation.widgets.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.lgtm.weathercaster.data.WeatherVO
import com.lgtm.weathercaster.presentation.WeatherUiState


// 결국 얘네를 어떻게 구성해서 화면에 그릴것인가?
// API 와 네이티브 사이의 서버에서 구성해서 내려주면 좋겠다.
// 지금은 앱에서 하드코딩하는 수밖에...

abstract class WeatherVH(
    viewBinding: ViewBinding,
) : RecyclerView.ViewHolder(viewBinding.root) {

    abstract fun bindData(data: WeatherVO)

    abstract val viewType: Int

}