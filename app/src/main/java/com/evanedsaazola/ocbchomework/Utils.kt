package com.evanedsaazola.ocbchomework

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun getReadableDate(date: Date): String {
    val datePattern = "dd MMM yyyy"
    val simpledateFormatter = SimpleDateFormat(datePattern, Locale.US)
    return simpledateFormatter.format(date)
}

fun currencyFormatter(amountValue: Double?): String {
    return "S${NumberFormat.getCurrencyInstance(Locale.US).format(amountValue)}"
}

fun positiveCurrencyFormatter(amountValue: Double?): String {
    return "+S${NumberFormat.getCurrencyInstance(Locale.US).format(amountValue)}"
}

fun negativeCurrencyFormatter(amountValue: Double?): String {
    return "-S${NumberFormat.getCurrencyInstance(Locale.US).format(amountValue)}"
}