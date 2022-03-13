package com.evanedsaazola.ocbchomework

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    fun saveJwtToken(token: String) {
        val prefEditor = prefs.edit()
        prefEditor.putString(Constants.USER_TOKEN, token)
        prefEditor.apply()
    }

    fun getJwtToken(): String? {
        return prefs.getString(Constants.USER_TOKEN, null)
    }

}