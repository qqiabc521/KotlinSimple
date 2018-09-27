package com.ljj.kotlinsimple.feed.page.contract

import com.ljj.comm.entity.Relationship
import com.ljj.comm.mvp.IViewDelegate
import com.ljj.kotlinsimple.feed.bean.FeedBean

interface FeedDetailContract {

    interface View : IViewDelegate{

        /**
         * 显示Feed详情信息
         */
        fun doFeedResult(feedBean: FeedBean)

        /**
         * 显示用户关系UI
         */
        fun doRelationship(relationship: Relationship)
    }

    interface Presenter{

        /**
         * 请求Feed详情信息
         */
        fun requestFeed(feedId: Long)
    }
}