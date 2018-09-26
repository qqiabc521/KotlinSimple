package com.ljj.kotlinsimple.feed.page.view

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.ljj.comm.entity.Relationship
import com.ljj.comm.mvp.view.BaseViewDelegate
import com.ljj.kotlinsimple.feed.R
import com.ljj.kotlinsimple.feed.bean.FeedBean

class FeedDetailViewDelegate : BaseViewDelegate() {
    private lateinit var contentTV: TextView
    private lateinit var onwerTV: TextView
    private lateinit var toUserBtn: Button
    private lateinit var followBtn: Button

    override val rootLayoutId: Int
        get() = R.layout.activity_feed_detail

    override fun initWidget() {
        contentTV = get(R.id.feed_detail_content)
        onwerTV = get(R.id.feed_detail_owner_name)
        toUserBtn = get(R.id.feed_detail_to_user)
        followBtn = get(R.id.feed_detail_follow_user)
    }

    fun doFeedResult(feedBean: FeedBean) {
        setActionBarTitle(feedBean.title)

        contentTV.text = feedBean.content
        val userBrief = feedBean.owner
        if (userBrief != null) {
            if (Relationship.OWNER == userBrief.relationship) {
                onwerTV.text = "来自：自己"
                toUserBtn.visibility = View.GONE
                followBtn.visibility = View.GONE
            } else {
                onwerTV.text = "来自：" + userBrief.name + " 用户"
                toUserBtn.visibility = View.VISIBLE
                followBtn.visibility = View.VISIBLE
                doRelationship(userBrief.relationship)
            }
        }
    }

    fun doRelationship(relationship: Relationship) {
        if (Relationship.FOLLOWED == relationship) {
            followBtn.text = "取消关注"
        } else if (Relationship.DEFAULT == relationship) {
            followBtn.text = "关注"
        }
    }

}
