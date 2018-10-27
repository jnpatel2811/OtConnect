package com.jnpatel2811.otconnect.login

object LoginApiHelper {

    fun doLogin(loginListener: LoginListener) {
        Thread(Runnable {
            try {
                Thread.sleep(3000)
            } catch (ignored: Exception) {
            }

            // TODO here make actual api call using retrofit to make user login
            loginListener.onSuccess()
        }).start()
    }

    interface LoginListener {
        fun onSuccess()
        fun onError(t: Throwable)
    }
}