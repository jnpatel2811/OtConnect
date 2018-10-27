package com.jnpatel2811.otconnect.feature.feed.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jnpatel2811.otconnect.App

class FeedListViewModelFactory(val app: App) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedListViewModel::class.java)) {
            return modelClass.getConstructor(App::class.java).newInstance(app)
        }
        throw IllegalStateException("Unknown ViewModel class.")
    }

}