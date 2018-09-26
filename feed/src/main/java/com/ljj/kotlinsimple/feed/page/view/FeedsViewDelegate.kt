package com.ljj.kotlinsimple.feed.page.view

import android.graphics.Rect
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.ljj.comm.bean.FeedBrief
import com.ljj.comm.mvp.view.BaseViewDelegate
import com.ljj.kotlinsimple.feed.R
import com.ljj.kotlinsimple.feed.page.adapter.FeedAdapter

class FeedsViewDelegate : BaseViewDelegate() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mFeedAdapter: FeedAdapter

    override val rootLayoutId: Int
        get() = R.layout.activity_feeds

    override fun initWidget() {
        mRecyclerView = get(R.id.feeds_recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(getActivity())
        mRecyclerView.addItemDecoration(SpacesItemDecoration(1))

        mRecyclerView.addItemDecoration(DividerItemDecoration(getActivity(), LinearLayout.VERTICAL))
        mRecyclerView.setHasFixedSize(true)
    }

    fun bindFeedAdapter(feedAdapter: FeedAdapter) {
        this.mFeedAdapter = feedAdapter
        mRecyclerView.adapter = feedAdapter
    }

    fun doFeedsResult(feedBriefs: List<FeedBrief>) {
        mFeedAdapter.addAll(feedBriefs)
        mFeedAdapter.notifyDataSetChanged()
    }


    inner class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
            outRect.set(space, space, space, space)
        }
    }
}
