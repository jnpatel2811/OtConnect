package com.jnpatel2811.otconnect.feature.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jnpatel2811.otconnect.R
import com.jnpatel2811.otconnect.base.BaseActivity
import com.jnpatel2811.otconnect.feature.feed.ui.FeedActivity
import com.jnpatel2811.otconnect.feed.login.Contract
import com.jnpatel2811.otconnect.feed.login.LoginPresenter
import com.jnpatel2811.otconnect.helpers.Utils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via username/password.
 */
class LoginActivity : BaseActivity(), Contract.View {

    private val presenter by lazy {
        LoginPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /* TODO check if already logged in, then navigate to dashboard.
         if (AccountManager.isUserLoggedIn()) {
             FeedActivity.startMe(mActivity)
             finish()
             return
         }*/
        signInBtn.setOnClickListener {
            presenter.doLogin("jay", "patel") // dummy data
        }
    }

    // TODO We should show proper Loading UI here using SpinKitView or something..
    override fun showProgressBar(loadingMsg: String?) {
        runOnUiThread {
            Utils.showProgressDialog(
                mActivity,
                getString(R.string.label_please_wait),
                if (loadingMsg.isNullOrBlank()) getString(R.string.msg_signing_in) else loadingMsg!!,
                null,
                false
            )
        }
    }

    override fun hideProgressBar() {
        runOnUiThread {
            Utils.dismissProgressDialog()
        }
    }

    override fun onLoggedIn(username: String) {
        runOnUiThread {
            signInBtn?.isEnabled = true
            hideProgressBar()
            Utils.hideKeyboard(mActivity)
            showInfoSnackBar("Login succeed.")
//        AccountManager.setLoggedInUserName("jay") // todo take it from data model
            FeedActivity.startMe(mActivity)
        }
    }

    override fun onLoginProcessStarted() {
        runOnUiThread {
            signInBtn?.isEnabled = false
            showProgressBar(getString(R.string.msg_signing_in))
        }
    }

    override fun onInvalidDataInput(errorMsg: String?) {
        runOnUiThread {
            showToast(if (errorMsg.isNullOrBlank()) getString(R.string.msg_something_went_wrong_please_try_again_later) else errorMsg!!)
        }
    }

    override fun onLoginError(e: Exception?, errorMsg: String?) {
        runOnUiThread {
            val errMsg =
                if (errorMsg.isNullOrBlank()) getString(R.string.msg_something_went_wrong_please_try_again_later) else errorMsg!!
            showInfoSnackBar("Login failed. Reason : Some $errMsg!")
        }
    }

    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}
