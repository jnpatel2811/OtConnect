package com.jnpatel2811.otconnect.feature.feed.data

import com.jnpatel2811.otconnect.feature.feed.models.Post
import com.jnpatel2811.otconnect.feature.feed.models.PostListResult

/**
 * class that deals with getting posts from data source, such as DB or Direbase
 *
 * TODO here get actual data from Firebase
 */
object FeedPostsRepo {

    fun getDummyPosts(): PostListResult {
        val postArrayList = arrayListOf<Post>()
        try {
            for (i in 1..17) {
                val post = Post()
                post.imagePath = "https://d73xd4ooutekr.cloudfront.net/v4/img/cover-photos/cover-photo-" +
                        String.format("%03d", i) + ".jpg"
                if (i in 1..3) {
                    post.isHot = true
                }

                if (i in 5..7) {
                    post.isNot = true
                }
                postArrayList.add(post)
            }
        } catch (e: Exception) {
            // TODO log exception here
        }

        val postListResult = PostListResult()
        postListResult.posts = postArrayList
        return postListResult
    }
}