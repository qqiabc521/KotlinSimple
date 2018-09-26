package com.ljj.comm.mvp

import io.reactivex.disposables.Disposable

/**
 * RequestCallBack接口的抽象类，使RequestCallBack的实现者只关注onResponse回调，其他三个功能回调在公共基类统一处理。
 */

abstract class AbstractRequestCallBack<T>(private val mPresenterDelegate: PresenterDelegate?) : RequestCallBack<T> {

    override fun onRequestStart(disposable: Disposable) {
        mPresenterDelegate?.onRequestStart(getTaskId(), disposable)
    }

    override fun onFinish() {
        mPresenterDelegate?.onFinish(getTaskId())
    }

    override fun onRequestError(error: String) {
        mPresenterDelegate?.onRequestError(getTaskId(), error)
    }

    protected fun getTaskId(): String? {
        return null
    }
}
