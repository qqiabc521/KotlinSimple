package com.ljj.kotlinsimple.feed.page.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.ljj.comm.adapter.BaseRecyclerViewAdapter
import com.ljj.comm.adapter.BaseRecyclerViewHolder
import com.ljj.comm.bean.FeedBrief
import com.ljj.kotlinsimple.feed.R

import java.lang.ref.WeakReference

class FeedAdapter(activity: Activity) : BaseRecyclerViewAdapter<FeedBrief>() {
    private val mActivity: WeakReference<Activity> = WeakReference(activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<*> {
        return FeedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false))
    }

    inner class FeedViewHolder(itemView: View) : BaseRecyclerViewHolder<FeedBrief>(itemView) {
        private val titleTV: TextView = itemView.findViewById(R.id.feed_item_title)

        /**
         * @param feedBrief   the data of bind
         * @param position the item position of recyclerView
         */
        override fun onBindViewHolder(feedBrief: FeedBrief?, position: Int) {
            if (feedBrief == null) {
                return
            }
            mActivity.get() ?: return
            titleTV.text = feedBrief.title
        }

    }
}
