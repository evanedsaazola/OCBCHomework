package com.evanedsaazola.ocbchomework

import android.annotation.SuppressLint
import java.text.ParseException
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.getReadableDate(): String {
    val parser = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val formatter = java.text.SimpleDateFormat("dd MMM yyyy")
    try {
        val calendar = Calendar.getInstance().also {
            it.time = parser.parse(this)
        }
        val result = formatter.format(calendar.time)
        return result
    } catch (e: ParseException) {
        print(e)
        return this
    } catch (e: Exception) {
        print(e)
        return this
    }
}