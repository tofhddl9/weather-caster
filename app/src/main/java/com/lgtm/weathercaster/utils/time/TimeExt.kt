package com.lgtm.weathercaster.utils.time

import java.time.DayOfWeek

fun DayOfWeek.toKorean(): String {
    return when (this) {
        DayOfWeek.SUNDAY -> "일"
        DayOfWeek.MONDAY -> "월"
        DayOfWeek.TUESDAY -> "화"
        DayOfWeek.WEDNESDAY -> "수"
        DayOfWeek.THURSDAY -> "목"
        DayOfWeek.FRIDAY -> "금"
        DayOfWeek.SATURDAY -> "토"
    }
}

fun hourToStringFormat(hourIn24: Int): String {
    return when {
        hourIn24 == 12 -> {
            "오후 12시"
        }
        hourIn24 == 0 -> {
            "오전 0시"
        }
        hourIn24 > 12 -> {
            "오후 ${hourIn24 - 12}시"
        }
        else -> {
            "오전 ${hourIn24}시"
        }
    }
}