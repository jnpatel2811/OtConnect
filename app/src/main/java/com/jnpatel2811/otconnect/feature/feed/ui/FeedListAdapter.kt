package com.jnpatel2811.otconnect.feature.feed.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jnpatel2811.otconnect.R
import com.jnpatel2811.otconnect.feature.feed.models.Post
import com.jnpatel2811.otconnect.feature.feed.viewmodel.FeedListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.feed_post_item.view.*

class FeedListAdapter(var context: Context, var posts: ArrayList<Post>, val viewModel: FeedListViewModel) :
    RecyclerView.Adapter<FeedListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.feed_post_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]

        Picasso.Builder(context).loggingEnabled(true)

        Picasso.get()
            .load(post.imageUrl)
            .resize(260, 180)
            .centerInside()
            .into(holder.itemView.image)

        holder.itemView.btnHot.setBackgroundColor(
            ContextCompat.getColor(
                context,
                if (post.isHot()) android.R.color.black else android.R.color.darker_gray
            )
        )

        holder.itemView.btnNot.setBackgroundColor(
            ContextCompat.getColor(
                context,
                if (post.isNot()) android.R.color.black else android.R.color.darker_gray
            )
        )

        holder.itemView.tag = post
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.itemView.btnHot.setBackgroundColor(ContextCompat.getColor(context, android.R.color.darker_gray))
        holder.itemView.btnNot.setBackgroundColor(ContextCompat.getColor(context, android.R.color.darker_gray))
        holder.itemView.image.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_light))
        holder.itemView.image.setImageDrawable(null)
    }

    fun dispatchUpdates(newPosts: ArrayList<Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}