package com.lgtm.weathercaster.utils

import android.content.res.Resources
import androidx.core.os.ConfigurationCompat
import java.time.Clock
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TimeProvider(
    private val clock: Clock = Clock.systemDefaultZone(),
    private val zoneId: ZoneId? = ZoneId.systemDefault(),
    locale: Locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0),
) {

    private val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        .withLocale(locale)
        .withZone(zoneId)

    private val now: Long
        get() = clock.millis()

    private fun zdt(dt: Long = now): ZonedDateTime {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(dt), zoneId)
    }

    fun getCurrentTimeMillis(): Long {
        return now
    }

    fun getCurrentTimeInISO8601(): String {
        return zdt().format(format)
    }

    fun getMonth(dt: Long): Int {
        return zdt(dt).monthValue
    }

    fun getDayOfMonth(dt: Long): Int {
        return zdt(dt).dayOfMonth
    }

    fun getDayWeek(dt: Long): DayOfWeek {
        return zdt(dt).dayOfWeek
    }

    fun isToday(dt: Long): Boolean {
        return zdt(dt).toLocalDate().equals(LocalDate.now(zoneId))
    }

}

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
