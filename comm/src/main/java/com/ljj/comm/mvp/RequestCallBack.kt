package com.ljj.comm.mvp

import io.reactivex.disposables.Disposable

/**
 * Created by lijunjie on 2017/12/27.
 *
 * P层与M层的数据接口回调
 */

interface RequestCallBack<T> {

    /**
     * 请求开始回调
     * @param disposable
     */
    fun onRequestStart(disposable: Disposable)

    /**
     * 请求完成回调
     */
    fun onFinish()

    /**
     * 请求结果回调
     * @param data
     */
    fun onResponse(data: T)

    /**
     * 请求异常回调
     * @param error
     */
    fun onRequestError(error: String)
}
