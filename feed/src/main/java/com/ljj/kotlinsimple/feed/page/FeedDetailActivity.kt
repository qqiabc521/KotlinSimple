package com.ljj.kotlinsimple.feed.page

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.ljj.comm.busbean.UpdateRelationship
import com.ljj.comm.entity.Relationship
import com.ljj.comm.model.UserAssistModel
import com.ljj.comm.mvp.AbstractRequestCallBack
import com.ljj.comm.mvp.presenter.ActivityPresenter
import com.ljj.comm.rxbus.RxBus
import com.ljj.comm.util.RxUtils
import com.ljj.kotlinsimple.feed.R
import com.ljj.kotlinsimple.feed.bean.FeedBean
import com.ljj.kotlinsimple.feed.model.FeedModel
import com.ljj.kotlinsimple.feed.page.view.FeedDetailViewDelegate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer

//@Route(path="/feed/activity/feed_detail")
class FeedDetailActivity : ActivityPresenter<FeedDetailViewDelegate>(), View.OnClickListener {
    private var feedBean: FeedBean? = null

    private val userAssistModel : UserAssistModel by lazy {
        ARouter.getInstance().navigation(UserAssistModel::class.java)
    }

    private val feedModel : FeedModel by lazy {
        ARouter.getInstance().navigation(FeedModel::class.java)
    }

    override val delegateClass: Class<FeedDetailViewDelegate>
        get() = FeedDetailViewDelegate::class.java

    override fun onCreateBefore(savedInstanceState: Bundle?) {
        register(RxBus.getDefault().register(UpdateRelationship::class.java, Consumer<UpdateRelationship> { updateRelationship ->
            if (updateRelationship == null) {
                return@Consumer
            }
            val userId = updateRelationship.userId!!
            if (feedBean == null || feedBean!!.owner == null || feedBean!!.owner!!.id != userId) {
                return@Consumer
            }

            val relationship = updateRelationship.relationship
            feedBean!!.owner!!.relationship = relationship!!

            if (Relationship.DEFAULT == relationship) {
                updateRelationship(userId, relationship)
            } else if (Relationship.FOLLOWED == relationship) {
                updateRelationship(userId, relationship)
            }
        }, AndroidSchedulers.mainThread()))

    }

    override fun onCreateAfter() {
        viewDelegate.setOnClickListenter(this,
                R.id.feed_detail_to_user,
                R.id.feed_detail_follow_user)

        val feedId = intent.getLongExtra(FEED_ID, -1)
        requestFeed(feedId)
    }

    private fun requestFeed(feedId: Long) {
        register(RxUtils.defaultCallback(feedModel.getFeed(feedId).map { feedEntity -> FeedBean(feedEntity) }, object : AbstractRequestCallBack<FeedBean>(this) {
            /**
             * 请求结果回调
             *
             * @param data
             */
            override fun onResponse(data: FeedBean) {
                feedBean = data
                viewDelegate.doFeedResult(data)
            }
        }))
    }

    private fun updateRelationship(userId: Long, relationship: Relationship?) {
        if (feedBean == null || feedBean!!.owner == null) {
            return
        }
        val userBrief = feedBean!!.owner
        if (userId != userBrief!!.id) {
            return
        }
        feedBean!!.updateRelationship(relationship!!)
        viewDelegate.doRelationship(relationship)
    }

    private fun toFollowUser(userId: Long) {
        updateUserRelationship(userId, Relationship.FOLLOWED)
    }

    private fun toUnFollowUser(userId: Long) {
        updateUserRelationship(userId, Relationship.DEFAULT)
    }

    private fun updateUserRelationship(userId: Long, relationship: Relationship) {
        userAssistModel.updateUserRelation(userId, relationship, object : AbstractRequestCallBack<Boolean>(this) {
            override fun onResponse(data: Boolean) {
                RxBus.getDefault().post(UpdateRelationship(userId, relationship))
            }
        })
    }

    override fun onClick(v: View) {
        if (v.id == R.id.feed_detail_to_user) {
            ARouter.getInstance().build("/user/activity/user_detail").withParcelable("user", feedBean!!.owner).navigation(this)
        } else if (v.id == R.id.feed_detail_follow_user) {
            val userBrief = feedBean!!.owner
            if (userBrief != null) {
                if (Relationship.DEFAULT == userBrief.relationship) {
                    toFollowUser(userBrief.id)
                } else if (Relationship.FOLLOWED == userBrief.relationship) {
                    toUnFollowUser(userBrief.id)
                }
            }

        }
    }

    companion object {

        const val FEED_ID = "feed_id"
    }


}
