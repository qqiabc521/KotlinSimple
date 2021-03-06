package com.ljj.comm.mvp

import io.reactivex.disposables.Disposable

/**
 * Presenter的一个委派类。
 * 为了简化RequestCallBack的实现类，使RequestCallBack的实现类只关注onResponse结果，使通用的操作在基类中统一处理。
 */

interface PresenterDelegate {

    fun onRequestStart(taskId: String?, disposable: Disposable)

    fun onFinish(taskId: String?)

    fun onRequestError(taskId: String?, error: String)

}
