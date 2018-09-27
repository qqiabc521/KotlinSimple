package com.ljj.kotlinsimple.feed.page.contract

import com.ljj.comm.bean.FeedBrief
import com.ljj.comm.mvp.IViewDelegate
import com.ljj.comm.mvp.contract.AdapterOnItemLisenter

interface FeedsContract {

    interface View : IViewDelegate {

        /**
         * 设置Feed列表监听回调
         */
        fun setFeedsItemLisenter(feedItemListenter: AdapterOnItemLisenter<FeedBrief>)

        /**
         * 展示Feed数据集合UI
         */
        fun doFeedsResult(feedBriefs: List<FeedBrief>)
    }

    interface Presenter {

        /**
         * 获得Feed列表数据
         */
        fun requestFeeds()
    }

}