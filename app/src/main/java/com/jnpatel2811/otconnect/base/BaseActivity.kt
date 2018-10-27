package com.jnpatel2811.otconnect.base

import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jnpatel2811.otconnect.helpers.Utils

/**
 * Base class for all the activities.
 * Right now this provides few basic things, but it can handle various stuff when required
 * (like handling user should be logged in to access the activity which is extending this).
 */
open class BaseActivity : AppCompatActivity() {

    protected val mActivity: Activity by lazy {
        this
    }

    protected fun showInfoSnackBar(msg: String) {
        val snackBar = Snackbar.make(
            findViewById<View>(android.R.id.content),
            msg, Snackbar.LENGTH_INDEFINITE
        )

        snackBar.setAction("Ok") { snackBar.dismiss() }
        snackBar.show()
    }

    protected fun showToast(msg: String) {
        Utils.showToast(mActivity.applicationContext, msg)
    }
}