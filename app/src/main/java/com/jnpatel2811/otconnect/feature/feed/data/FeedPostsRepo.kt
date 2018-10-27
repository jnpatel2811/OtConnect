package com.jnpatel2811.otconnect.feature.feed.data

import com.jnpatel2811.otconnect.feature.feed.models.Post
import com.jnpatel2811.otconnect.feature.feed.models.PostListResult
import com.jnpatel2811.otconnect.helpers.Utils

/**
 * class that deals with getting posts from data source, such as DB or Direbase
 *
 * TODO here get actual data from Firebase
 */
object FeedPostsRepo {

    private val postArrayList by lazy { arrayListOf<Post>() }

    fun getDummyPosts(numberOfPosts: Int = 20): PostListResult {
        try {
            if (postArrayList.isEmpty()) {
                for (i in 1..numberOfPosts) {
                    val post = Post()
                    post.imageUrl = Utils.getRandomImageUrl()
                    if (i in 2..4) {
                        post.isHot = true
                    }

                    if (i in 6..9) {
                        post.isNot = true
                    }
                    postArrayList.add(post)
                }
            }
        } catch (e: Exception) {
            // TODO log exception here
        }

        val postListResult = PostListResult()
        postListResult.posts = postArrayList
        return postListResult
    }

    fun addPost(imageUrl: String) {
        val post = Post()
        post.imageUrl = imageUrl
        postArrayList.add(0, post)
    }
}