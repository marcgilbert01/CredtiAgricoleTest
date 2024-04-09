package net.freshclouds.domain.utils

import net.bankingapp.domain.utils.DateUtils
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class DateUtilsImpl : DateUtils {

    private val dateTimeFormat = "yyyy-MM-dd"

    override fun convertLongToLocalReadableString(timeStamp: Long): String {
        return LocalDateTime
            .ofInstant(Instant.ofEpochMilli(timeStamp), ZoneOffset.systemDefault())
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern(dateTimeFormat))
    }
}