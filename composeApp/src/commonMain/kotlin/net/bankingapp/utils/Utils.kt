package net.bankingapp.utils

import net.freshclouds.domain.utils.DateUtilsImpl

val dateUtilsForUi = DateUtilsImpl()

fun Long.convertToDate(): String {
    return dateUtilsForUi.convertLongToLocalReadableString(this)
}