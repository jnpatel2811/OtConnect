package com.jnpatel2811.otconnect.helpers.extentions

import android.content.Context
import android.content.SharedPreferences
import com.jnpatel2811.otconnect.Constants

fun Context.getPreference(): SharedPreferences? = this.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
