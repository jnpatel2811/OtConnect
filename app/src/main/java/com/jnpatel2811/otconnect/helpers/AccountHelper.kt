package com.jnpatel2811.otconnect.helpers

import com.jnpatel2811.otconnect.App
import com.jnpatel2811.otconnect.helpers.extentions.getPreference

object AccountManager {

    private const val PERSON_ID_STORE_KEY = "AccountManager:username"

    fun setUsername(personId: String) {
        App.context.getPreference()?.edit()?.putString(PERSON_ID_STORE_KEY, personId)?.apply()
    }

    fun isUserLoggedIn(): Boolean {
        return !getLoggedInUserName().isNullOrBlank()
    }

    // Username of logged in user. Null means user is not logged in
    const val LOGGED_IN_USERNAME = "com.jnpatel2811.username"

    fun getLoggedInUserName(): String? {
        return App.context.getPreference()?.getString(LOGGED_IN_USERNAME, null)
    }

    fun setLoggedInUserName(username: String) {
        App.context.getPreference()?.edit()?.putString(LOGGED_IN_USERNAME, username)?.apply()
    }

    fun doLogout() {
        App.context.getPreference()?.edit()?.clear()?.apply()
    }
}