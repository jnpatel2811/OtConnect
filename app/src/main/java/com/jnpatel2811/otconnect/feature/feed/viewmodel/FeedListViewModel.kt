package com.jnpatel2811.otconnect.feature.feed.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.jnpatel2811.otconnect.App
import com.jnpatel2811.otconnect.base.BaseViewModel
import com.jnpatel2811.otconnect.feature.feed.data.FeedPostsRepo
import com.jnpatel2811.otconnect.helpers.Utils

class FeedListViewModel(application: App) : BaseViewModel(application) {
    val viewStateLiveData: MutableLiveData<ViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = ViewState()
    }

    fun getPosts() {
        val viewState = ViewState()
        viewState.posts = FeedPostsRepo.getDummyPosts(25).posts
        viewStateLiveData.postValue(viewState)
    }

    fun addPost() {
        FeedPostsRepo.addPost(Utils.getImageUrl(Utils.getRandomNumber(2)))
    }
}