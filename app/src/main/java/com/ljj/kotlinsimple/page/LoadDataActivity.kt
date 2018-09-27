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
import com.ljj.kotlinsimple.page.contract.LoadDataContract
import com.ljj.kotlinsimple.page.view.LoadDataViewDelegate
import com.ljj.kotlinsimple.user.bean.UserBean
import com.ljj.kotlinsimple.user.model.UserModel
import com.ljj.kotlinsimple.util.SimpleSetting
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class LoadDataActivity : ActivityPresenter<LoadDataContract.ViewDelegate>(),LoadDataContract.Presenter {

    private val feedModel : FeedModel by lazy {
        ARouter.getInstance().navigation(FeedModel::class.java)
    }

    private val userModel : UserModel by lazy {
        ARouter.getInstance().navigation(UserModel::class.java)
    }

    override val getDelegateView: LoadDataContract.ViewDelegate
        get() = LoadDataViewDelegate()

    override fun onCreateBefore(savedInstanceState: Bundle?) {

    }

    override fun onCreateAfter() {
        loadData()
    }

    /**
     * 载入初始化数据
     */
    override fun loadData() {
        if (SimpleSetting.isLoadedData) {
            toHomePage()
        } else {
            startLoadObservable()
        }
    }

    /**
     * 进入主页
     */
    override fun toHomePage() {
        startActivity(Intent(this@LoadDataActivity, MainActivity::class.java))
        finish()
    }

    private fun startLoadObservable() {
        register(RxUtils.defaultCallback(INIT_LOAD_DATA, Observable.zip(saveUsersObservable(AppDataConfig.createUsers(COUNT)),
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
                viewDelegate.doLoadDataCompleted()
                toHomePage()
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

    override fun onRequestStart(taskId: String?, disposable: Disposable) {
        if(INIT_LOAD_DATA == taskId){
            return
        }
        super.onRequestStart(taskId, disposable)
    }

    companion object {

        private const val COUNT = 9
        private const val INIT_LOAD_DATA: String = "init_load_data"
    }
}
