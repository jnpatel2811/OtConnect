package com.jnpatel2811.otconnect.feature.feed.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.jnpatel2811.otconnect.App
import com.jnpatel2811.otconnect.base.BaseViewModel
import com.jnpatel2811.otconnect.feature.feed.data.FeedPostsRepo

internal class FeedListViewModel(application: App) : BaseViewModel(application) {
    val viewStateLiveData: MutableLiveData<ViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = ViewState()
    }

    fun getPosts() {
        val viewState = ViewState()
        viewState.posts = FeedPostsRepo.getDummyPosts().posts
        viewStateLiveData.postValue(viewState)
    }
}