package com.jnpatel2811.otconnect.feature.feed.models

class Post {
    var imageUrl: String? = null
    var isHot: Boolean? = null
    var isNot: Boolean? = null

    fun isHot(): Boolean {
        return isHot?: false
    }

    fun isNot(): Boolean {
        return isNot?: false
    }
}