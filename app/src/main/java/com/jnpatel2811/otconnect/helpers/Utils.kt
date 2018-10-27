package com.jnpatel2811.otconnect.helpers

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import java.util.*

object Utils {

    private var mProgressDialog: ProgressDialog? = null

    fun showToast(context: Context, msg: String, toastLength: Int = Toast.LENGTH_SHORT) {
        try {
            Toast.makeText(context, msg, toastLength)?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setTextAndHandleVisibility(text: String?, textView: TextView?) {
        try {
            if (textView != null) {
                if (text == null || text.isBlank()) {
                    textView.visibility = GONE
                } else {
                    textView.text = text
                    textView.visibility = VISIBLE
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showProgressDialog(ctx: Context, title: String?, body: String, icon: Drawable? = null, isCancellable: Boolean) {
        try {
            if (ctx is Activity && !ctx.isFinishing) {
                mProgressDialog = ProgressDialog.show(ctx, title, body, true)
                mProgressDialog?.setIcon(icon)
                mProgressDialog?.setCancelable(isCancellable)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dismissProgressDialog() {
        try {
            mProgressDialog?.dismiss()
            mProgressDialog = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showAlertDialog(ctx: Context?, title: String? = null, body: String? = null, okListener: DialogInterface.OnClickListener? = null) {
        try {
            if (ctx != null) {
                val builder = AlertDialog
                    .Builder(ctx)
                    .setMessage(body)
                    .setPositiveButton(
                        "OK",
                        okListener
                            ?: DialogInterface.OnClickListener { dialog, _ -> dialog?.dismiss() }
                    )
                if (!TextUtils.isEmpty(title)) {
                    builder.setTitle(title)
                }

                builder.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
     * dialog.
     *
     * @param ctx
     * @param title       Title to be shown in the dialog.
     * @param message     Message to be shown in the dialog.
     * @param yesListener Yes click handler
     * @param noListener
     * @param yesLabel    Label for yes button
     * @param noLabel     Label for no button
     */
    fun showConfirmDialog(ctx: Context, title: String?, message: String, yesListener: DialogInterface.OnClickListener?, noListener: DialogInterface.OnClickListener?, yesLabel: String, noLabel: String) {
        var yesListener = yesListener
        var noListener = noListener

        val builder = AlertDialog.Builder(ctx)

        if (yesListener == null) {
            yesListener = DialogInterface.OnClickListener { dialog, _ -> dialog?.dismiss() }
        }

        if (noListener == null) {
            noListener = DialogInterface.OnClickListener { dialog, _ -> dialog?.dismiss() }
        }

        if (title != null) {
            builder.setTitle(title)
        }

        builder.setMessage(message).setPositiveButton(yesLabel, yesListener).setNegativeButton(noLabel, noListener).show()
    }

    fun hideKeyboard(context: Context) {
        try {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow((context as Activity).currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // no-op
        }
    }

    fun showKeyboard(context: Context, fieldToFocus: View) {
        try {
            fieldToFocus.clearFocus()
            fieldToFocus.requestFocus()
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(fieldToFocus, InputMethodManager.SHOW_IMPLICIT)
        } catch (e: Exception) {
            // no-op
        }
    }

    fun generate24CharId(): String {
        return getRandomUuid(24)
    }

    /**
     * Returns random uuid(alpha numeric) with the specified length.
     *
     * @param length <=32
     * @return
     */
    private fun getRandomUuid(length: Int): String {
        if (length > 32) {
            throw IllegalStateException("Random string length cannot be more than than UUID class limit.")
        }
        val uuid = UUID.randomUUID().toString()
        return uuid.replace("-".toRegex(), "").substring(0, length)   // strip - from it and then return the substring
    }

}