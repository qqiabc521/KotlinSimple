package com.ljj.kotlinsimple.feed.page

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ljj.comm.bean.FeedBrief
import com.ljj.comm.entity.FeedEntity
import com.ljj.comm.mvp.contract.AdapterOnItemLisenter
import com.ljj.comm.mvp.presenter.ActivityPresenter
import com.ljj.comm.util.RxUtils
import com.ljj.kotlinsimple.feed.model.FeedModel
import com.ljj.kotlinsimple.feed.page.contract.FeedsContract
import com.ljj.kotlinsimple.feed.page.view.FeedsViewDelegate
import io.reactivex.functions.Function
import java.util.*

@Route(path = "/feed/activity/feeds")
class FeedsActivity : ActivityPresenter<FeedsContract.View>(), FeedsContract.Presenter, AdapterOnItemLisenter<FeedBrief> {

    private val feedModel: FeedModel by lazy {
        ARouter.getInstance().navigation(FeedModel::class.java)
    }

    override val getDelegateView: FeedsContract.View
        get() = FeedsViewDelegate()

    override fun onCreateBefore(savedInstanceState: Bundle?) {
        viewDelegate.setFeedsItemLisenter(this)
    }

    override fun onCreateAfter() {
        requestFeeds()
    }

    /**
     * 点击item回调
     */
    override fun onClickItemLisenter(bean: FeedBrief, position: Int) {
        val bundle = Bundle()
        bundle.putLong(FeedDetailActivity.FEED_ID, bean.id)
        val intent = Intent(this@FeedsActivity, FeedDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    /**
     * 获得Feed列表数据
     */
    override fun requestFeeds() {
        register(RxUtils.defaultCallback(feedModel.feeds.map(Function<List<FeedEntity>?, List<FeedBrief>> { feedEntities ->
            if (feedEntities == null || feedEntities.isEmpty()) {
                return@Function null
            }

            val feedBriefs = ArrayList<FeedBrief>(feedEntities.size)
            for (feedEntity in feedEntities) {
                val feedBrief = FeedBrief(feedEntity)
                feedBriefs.add(feedBrief)
            }

            feedBriefs
        }), this, object : RxUtils.RxResult<List<FeedBrief>> {
            override fun doResult(feedBriefs: List<FeedBrief>) {
                viewDelegate.doFeedsResult(feedBriefs)
            }

            override fun doCompleted() {

            }
        }))
    }
}
