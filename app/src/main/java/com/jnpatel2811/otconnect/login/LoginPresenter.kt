package com.jnpatel2811.otconnect.login


class LoginPresenter(val view: Contract.View) : Contract.Presenter {

    override fun doLogin(username: String, password: String) {
        // todo it should be data model here instead of individual data

        // TODO if data is invalid, return here error msg using data model's validation check method
        // view.onInvalidDataInput("dummy error msg")
        // return

        // Assuming valid data

        view.onLoginProcessStarted()

        LoginApiHelper.doLogin(object : LoginApiHelper.LoginListener {
            override fun onSuccess() {
                view.onLoggedIn(username)
            }

            override fun onError(e: Exception) {
                view.onLoginError(e)
            }
        })
    }
}