package com.jnpatel2811.otconnect.base

import android.annotation.SuppressLint
import android.arch.lifecycle.AndroidViewModel
import com.jnpatel2811.otconnect.App
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

@SuppressLint("StaticFieldLeak")
abstract class BaseViewModel(val app: App) : AndroidViewModel(app) {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
        super.onCleared()
    }

    fun addDisposible(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}