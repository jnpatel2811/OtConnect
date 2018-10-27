package com.jnpatel2811.otconnect.login

open class Contract {
    interface View {

        fun showProgressBar(loadingMsg: String? = null)

        fun hideProgressBar()

        fun onLoggedIn(username: String)

        fun onLoginProcessStarted()

        fun onInvalidDataInput(errorMsg: String? = null)

        fun onLoginError(e: Exception? = null, errorMsg: String? = null)
    }

    interface Presenter {
        fun doLogin(username: String, password: String)
    }
}