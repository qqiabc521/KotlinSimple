package com.ljj.kotlinsimple.feed.model

import com.alibaba.android.arouter.facade.template.IProvider
import com.ljj.comm.entity.FeedEntity
import com.ljj.comm.mvp.RequestCallBack

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface FeedModel : IProvider {

    /**
     * 获得Feed列表
     * @return
     */
    val feeds: Observable<List<FeedEntity>>

    /**
     * 添加一条Feed
     *
     * @param feedEntity
     * @param callBack
     * @return
     */
    fun saveFeed(feedEntity: FeedEntity, callBack: RequestCallBack<Long>): Disposable

    /**
     * 添加一条Feed
     *
     * @param feedEntity
     * @return
     */
    fun saveFeed(feedEntity: FeedEntity): Observable<Long>

    /**
     * 添加一组feed
     *
     * @param feedEntitys
     * @param callBack
     * @return
     */
    fun saveFeeds(feedEntitys: List<FeedEntity>, callBack: RequestCallBack<Nothing?>): Disposable

    /**
     * 添加一组feed
     *
     * @param feedEntitys
     * @return
     */
    fun saveFeeds(feedEntitys: List<FeedEntity>): Observable<Nothing?>

    /**
     * 获得Feed列表
     * @param callBack
     * @return
     */
    fun getFeeds(callBack: RequestCallBack<List<FeedEntity>>): Disposable

    /**
     * 获得Feed
     * @param feedId
     * @param callBack
     * @return
     */
    fun getFeed(feedId: Long, callBack: RequestCallBack<FeedEntity>): Disposable

    /**
     * 获得Feed
     * @param feedId
     * @return
     */
    fun getFeed(feedId: Long): Observable<FeedEntity>

}
