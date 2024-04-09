package net.bankingapp.ui.common

fun Double.formatAmount(): String {
    return "%.2f €".format(this)
}