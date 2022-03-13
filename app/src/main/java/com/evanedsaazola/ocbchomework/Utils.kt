package com.evanedsaazola.ocbchomework

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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
    return "+ S${NumberFormat.getCurrencyInstance(Locale.US).format(amountValue)}"
}

fun negativeCurrencyFormatter(amountValue: Double?): String {
    return "- S${NumberFormat.getCurrencyInstance(Locale.US).format(amountValue)}"
}

fun View.hideSoftKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}