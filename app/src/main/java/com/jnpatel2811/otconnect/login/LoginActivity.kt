package com.jnpatel2811.otconnect.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jnpatel2811.otconnect.R
import com.jnpatel2811.otconnect.base.BaseActivity
import com.jnpatel2811.otconnect.helpers.AccountManager
import com.jnpatel2811.otconnect.helpers.Utils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via username/password.
 */
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /* TODO check if already logged in, then navigate to dashboard.
         if (AccountManager.isUserLoggedIn()) {
             // open dashboard activity
             finish()
             return
         }*/
        signInBtn.setOnClickListener {
            attemptLogin()
        }
    }

    private fun attemptLogin() {
        signInBtn?.isEnabled = false
        Utils.hideKeyboard(mActivity)
        Utils.showProgressDialog(
            mActivity,
            getString(R.string.label_please_wait),
            getString(R.string.msg_signing_in),
            null,
            false
        )

        // todo need to send data model here
        LoginApiHelper.doLogin(object : LoginApiHelper.LoginListener {
            override fun onSuccess() {
                runOnUiThread {
                    signInBtn?.isEnabled = true
                    Utils.hideKeyboard(mActivity)
                    Utils.dismissProgressDialog()
                    AccountManager.setLoggedInUserName("jay") // todo take it from data model
                    showInfoSnackBar("Login succeed.")
                    // TODO open dashboard
                    //DashboardActivity.startMe(mActivity)
                    finish()
                }
            }

            override fun onError(t: Throwable) {
                runOnUiThread {
                    signInBtn?.isEnabled = true
                    Utils.hideKeyboard(mActivity)
                    Utils.dismissProgressDialog()
                    showInfoSnackBar("Login failed. Reason : Some error!")
                }
            }
        })
    }

    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}
