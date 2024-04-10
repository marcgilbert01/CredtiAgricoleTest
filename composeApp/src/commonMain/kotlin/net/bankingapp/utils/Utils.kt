package net.bankingapp.utils

import net.bankingapp.domain.utils.DateUtilsImpl

val dateUtilsForUi = DateUtilsImpl()

fun Long.convertToDate(): String {
    return dateUtilsForUi.convertEpochSecondsToLocalReadableDateString(this)
}