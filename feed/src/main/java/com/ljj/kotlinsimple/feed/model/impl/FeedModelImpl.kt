package com.ljj.kotlinsimple.feed.model.impl

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ljj.comm.config.Constants
import com.ljj.comm.db.DbService
import com.ljj.comm.entity.FeedEntity
import com.ljj.comm.mvp.RequestCallBack
import com.ljj.comm.util.RxUtils
import com.ljj.kotlinsimple.feed.model.FeedModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

@Route(path = "/feed/model/feed")
class FeedModelImpl : FeedModel {

    private val feedEntityDao = ARouter.getInstance().navigation(DbService::class.java).feedEntityDao

    /**
     * 获得Feed列表
     *\
     * @return
     */
    override val feeds: Observable<List<FeedEntity>>
        get() = Observable.just("").map { feedEntityDao.loadAll() }.delay(Constants.DELAY_TIME.toLong(), Constants.TIME_TYPE)

    /**
     * 添加一条Feed
     *
     * @param feedEntity
     * @param callBack
     * @return
     */
    override fun saveFeed(feedEntity: FeedEntity, callBack: RequestCallBack<Long>): Disposable {
        return RxUtils.defaultCallback(saveFeed(feedEntity), callBack)
    }

    /**
     * 添加一条Feed
     *
     * @param feedEntity
     * @return
     */
    override fun saveFeed(feedEntity: FeedEntity): Observable<Long> {
        return Observable.just(feedEntity).map { feedEntity -> feedEntityDao.insert(feedEntity) }.delay(Constants.DELAY_TIME.toLong(), Constants.TIME_TYPE)
    }

    /**
     * 添加一组feed
     *
     * @param feedEntitys
     * @param callBack
     * @return
     */
    override fun saveFeeds(feedEntitys: List<FeedEntity>, callBack: RequestCallBack<Nothing?>): Disposable {
        return RxUtils.defaultCallback(saveFeeds(feedEntitys), callBack)
    }

    /**
     * 添加一组feed
     *
     * @param feedEntitys
     * @return
     */
    override fun saveFeeds(feedEntitys: List<FeedEntity>): Observable<Nothing?> {
        return Observable.just(feedEntitys).map { feedEntitys ->
            feedEntityDao.insertInTx(feedEntitys)
            null
        }.delay(Constants.DELAY_TIME.toLong(), Constants.TIME_TYPE)
    }

    /**
     * 获得Feed列表
     *
     * @param callBack
     * @return
     */
    override fun getFeeds(callBack: RequestCallBack<List<FeedEntity>>): Disposable {
        return RxUtils.defaultCallback(feeds, callBack)
    }

    /**
     * 获得Feed
     *
     * @param feedId
     * @param callBack
     * @return
     */
    override fun getFeed(feedId: Long, callBack: RequestCallBack<FeedEntity>): Disposable {
        return RxUtils.defaultCallback(getFeed(feedId), callBack)
    }

    /**
     * 获得Feed
     *
     * @param feedId
     * @return
     */
    override fun getFeed(feedId: Long): Observable<FeedEntity> {
        return Observable.just(feedId).map { feedEntityDao.load(feedId) }.delay(Constants.DELAY_TIME.toLong(), Constants.TIME_TYPE)
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    override fun init(context: Context) {

    }
}
