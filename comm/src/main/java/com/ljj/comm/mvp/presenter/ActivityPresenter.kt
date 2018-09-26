package com.ljj.comm.mvp.presenter

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.ljj.comm.mvp.IViewDelegate
import com.ljj.comm.mvp.PresenterDelegate
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ActivityPresenter<T : IViewDelegate> : AppCompatActivity(), PresenterDelegate {

    protected lateinit var viewDelegate: T
        private set

    private val mDisposables = CompositeDisposable()

    protected abstract val delegateClass: Class<T>

    val tag: String
        get() = this.javaClass.simpleName

    init {
        try {
            viewDelegate = delegateClass.newInstance()
        } catch (e: InstantiationException) {
            RuntimeException(e)
        } catch (e: IllegalAccessException) {
            RuntimeException(e)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDelegate.create(layoutInflater, null, savedInstanceState)
        setContentView(viewDelegate!!.rootView)

        onCreateBefore(savedInstanceState)
        initToolbar()
        viewDelegate.initWidget()
        onCreateAfter()
    }

    protected fun initToolbar() {
        val toolbar = viewDelegate.toolbar
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }
    }

    protected abstract fun onCreateBefore(savedInstanceState: Bundle?)

    protected abstract fun onCreateAfter()

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (viewDelegate == null) {
            try {
                viewDelegate = delegateClass.newInstance()
            } catch (e: InstantiationException) {
                throw RuntimeException("create IDelegate error")
            } catch (e: IllegalAccessException) {
                throw RuntimeException("create IDelegate error")
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (viewDelegate!!.optionsMenuId != 0) {
            menuInflater.inflate(viewDelegate!!.optionsMenuId, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposables.dispose()
        viewDelegate.destory()
    }

    /**
     * 添加一个订阅关系
     *
     * @param d
     */
    @UiThread
    protected fun register(d: Disposable) {
        mDisposables.add(d)
    }

    /**
     * 手动移除并取消订阅一个订阅关系
     *
     * @param d
     */
    @UiThread
    protected fun unRegister(d: Disposable) {
        mDisposables.remove(d)
    }

    override fun onRequestStart(disposable: Disposable) {
        viewDelegate.showLoadingView()
    }

    override fun onFinish() {
        viewDelegate.hideLoadView()
    }

    override fun onRequestError(error: String) {
        viewDelegate.showNofityMessage(error)
    }
}