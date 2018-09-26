package com.ljj.kotlinsimple.page

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.ljj.comm.entity.FeedEntity
import com.ljj.comm.mvp.presenter.ActivityPresenter
import com.ljj.comm.util.AppLog
import com.ljj.comm.util.RxUtils
import com.ljj.kotlinsimple.config.AppDataConfig
import com.ljj.kotlinsimple.feed.bean.FeedBean
import com.ljj.kotlinsimple.feed.model.FeedModel
import com.ljj.kotlinsimple.page.view.LoadDataViewDelegate
import com.ljj.kotlinsimple.user.bean.UserBean
import com.ljj.kotlinsimple.user.model.UserModel
import com.ljj.kotlinsimple.util.SimpleSetting
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class LoadDataActivity : ActivityPresenter<LoadDataViewDelegate>() {

    private val feedModel = ARouter.getInstance().navigation(FeedModel::class.java)
    private val userModel = ARouter.getInstance().navigation(UserModel::class.java)

    override val delegateClass: Class<LoadDataViewDelegate>
        get() = LoadDataViewDelegate::class.java

    override fun onCreateBefore(savedInstanceState: Bundle?) {

    }

    override fun onCreateAfter() {
        loadData()
    }

    fun loadData() {
        if (SimpleSetting.isLoadedData) {
            loadCompleted()
        } else {
            startLoadObservable()
        }
    }

    private fun loadCompleted() {
        viewDelegate.doLoadDataCompleted()
        startActivity(Intent(this@LoadDataActivity, MainActivity::class.java))
        finish()
    }


    private fun startLoadObservable() {
        register(RxUtils.defaultCallback(Observable.zip(saveUsersObservable(AppDataConfig.createUsers(COUNT)),
                getFeedObservable(AppDataConfig.createFeeds(COUNT)),
                BiFunction<Long, FeedBean, FeedEntity> { id, feedBean ->
                    val feedEntity = FeedBean.createFeedEntity(feedBean)
                    feedEntity.ownerId = id
                    feedEntity
                }).concatMap { feedEntity ->
            feedModel.saveFeed(feedEntity)
        }, this, object : RxUtils.RxResult<Long> {
            override fun doResult(id: Long) {
                AppLog.e(tag, "saved feed" + id!!.toString())
                viewDelegate.doLoadingProgress(id!!.toInt(), COUNT)
            }

            override fun doCompleted() {
                SimpleSetting.isLoadedData = true
                loadCompleted()
            }
        }))
    }

    private fun getFeedObservable(feedBeans: List<FeedBean>): Observable<FeedBean> {
        return Observable.fromArray(*feedBeans.toTypedArray())
    }

    private fun saveUsersObservable(userBeans: List<UserBean>): Observable<Long> {
        return Observable.fromArray(*userBeans.toTypedArray()).flatMap { userBean ->
            var observable = userModel.saveUser(UserBean.createUserEntity(userBean)).subscribeOn(Schedulers.single())
            observable
        }
    }

    companion object {

        private val COUNT = 9
    }
}
