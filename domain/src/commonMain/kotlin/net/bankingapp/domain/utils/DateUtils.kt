package net.bankingapp.domain.utils

interface DateUtils {

    fun convertEpochSecondsToLocalReadableDateString(timeStamp: Long): String

}