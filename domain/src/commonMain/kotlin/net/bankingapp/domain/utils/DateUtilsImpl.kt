package net.bankingapp.domain.utils

import kotlinx.datetime.LocalDate

class DateUtilsImpl : DateUtils {

    override fun convertEpochSecondsToLocalReadableDateString(timeStamp: Long): String {
        val localDate = LocalDate.fromEpochDays((timeStamp/ 60/ 60/ 24).toInt())
        return "${localDate.dayOfMonth}/${localDate.monthNumber}/${localDate.year}"
    }
}