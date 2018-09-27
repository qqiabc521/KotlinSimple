package com.ljj.kotlinsimple.feed.page.view

import android.graphics.Rect
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.ljj.comm.bean.FeedBrief
import com.ljj.comm.mvp.contract.AdapterOnItemLisenter
import com.ljj.comm.mvp.view.BaseViewDelegate
import com.ljj.kotlinsimple.feed.R
import com.ljj.kotlinsimple.feed.page.adapter.FeedAdapter
import com.ljj.kotlinsimple.feed.page.contract.FeedsContract

class FeedsViewDelegate : BaseViewDelegate(), FeedsContract.View {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mFeedAdapter: FeedAdapter
    private var onItemOnItemLisenter: AdapterOnItemLisenter<FeedBrief>? = null

    override val rootLayoutId: Int
        get() = R.layout.activity_feeds

    override fun initWidget() {
        mRecyclerView = get(R.id.feeds_recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(getActivity())
        mRecyclerView.addItemDecoration(SpacesItemDecoration(1))

        mRecyclerView.addItemDecoration(DividerItemDecoration(getActivity(), LinearLayout.VERTICAL))
        mRecyclerView.setHasFixedSize(true)

        mFeedAdapter = FeedAdapter(getActivity())
        mFeedAdapter.setOnItemClickListener { feedBrief, position ->
            onItemOnItemLisenter!!.onClickItemLisenter(feedBrief,position)
        }
        mRecyclerView.adapter = mFeedAdapter
    }

    /**
     * 设置Feed列表监听回调
     */
    override fun setFeedsItemLisenter(feedItemListenter: AdapterOnItemLisenter<FeedBrief>) {
        onItemOnItemLisenter = feedItemListenter
    }

    /**\
     * 展示Feed数据集合UI
     */
    override fun doFeedsResult(feedBriefs: List<FeedBrief>) {
        mFeedAdapter.addAll(feedBriefs)
        mFeedAdapter.notifyDataSetChanged()
    }


    inner class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
            outRect.set(space, space, space, space)
        }
    }
}
