package com.jnpatel2811.otconnect.feature.feed.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jnpatel2811.otconnect.R

class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_feed)


    }


    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, FeedActivity::class.java)
            context.startActivity(intent)
        }
    }
}
