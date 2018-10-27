package com.jnpatel2811.otconnect.feature.feed.viewmodel

import com.jnpatel2811.otconnect.feature.feed.models.Post

class ViewState {
    var showError: Boolean = false
    var errorMessage: String? = null
    var posts: ArrayList<Post>? = null
}