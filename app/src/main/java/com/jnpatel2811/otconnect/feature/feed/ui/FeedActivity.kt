package com.jnpatel2811.otconnect.feature.feed.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jnpatel2811.otconnect.App
import com.jnpatel2811.otconnect.R
import com.jnpatel2811.otconnect.base.BaseActivity
import com.jnpatel2811.otconnect.feature.feed.viewmodel.FeedListViewModel
import com.jnpatel2811.otconnect.feature.feed.viewmodel.FeedListViewModelFactory
import com.jnpatel2811.otconnect.helpers.Utils
import kotlinx.android.synthetic.main.activity_new_feed.*

class FeedActivity : BaseActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, FeedListViewModelFactory(application as App)).get(FeedListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_feed)

        Utils.showProgressDialog(mActivity, getString(R.string.label_please_wait), "Loading feed...", null, true)

        viewModel.getPosts()

        viewModel.viewStateLiveData.observe(this, Observer {
            Utils.dismissProgressDialog()

            if (it?.showError!!) {
                if (it.errorMessage != null) {
                    showInfoSnackBar(it.errorMessage!!)
                } else {
                    showInfoSnackBar(getString(R.string.error_while_loading_feed))
                }
            }

            if (it.posts != null && it.posts!!.size > 0) {
                val adapter = feedList.adapter
                if (adapter != null) {
                    (adapter as FeedListAdapter).dispatchUpdates(it.posts!!)
                } else {
                    val newAdapter = FeedListAdapter(this@FeedActivity, it.posts!!, viewModel)
                    feedList.layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
                    feedList.adapter = newAdapter
                }
//            } else {
//                showInfoSnackBar(getString(R.string.error_no_feed))
            }
        })

        addPostFab?.setOnClickListener {
            viewModel.addPost()
            viewModel.getPosts()
        }


    }

    companion object {
        fun startMe(context: Context) {
            val intent = Intent(context, FeedActivity::class.java)
            context.startActivity(intent)
        }
    }
}
