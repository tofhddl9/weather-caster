package com.lgtm.weathercaster.utils.time

import java.time.DayOfWeek

interface TimeProvider {
    fun isToday(dt: Long): Boolean

    fun isNight(dt: Long): Boolean

    fun getCurrentTimeMillis(): Long

    fun getCurrentTimeInISO8601(): String

    fun getMonth(dt: Long): Int

    fun getDayOfMonth(dt: Long): Int

    fun getDayWeek(dt: Long): DayOfWeek

    fun getHour(dt: Long): Int
}
