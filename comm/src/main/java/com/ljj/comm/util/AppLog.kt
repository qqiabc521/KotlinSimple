package com.ljj.comm.util

import android.util.Log

import com.ljj.comm.BaseApplication


/**
 * Created by lijunjie on 2017/12/21.
 */

object AppLog {

    val LOG = AppLog::class.java.simpleName

    val isDebug: Boolean
        get() = BaseApplication.instance!!.isLoggable

    fun d(tag: String?, message: String?) {
        if (!isDebug || message == null)
            return
        Log.d(tag ?: LOG, message)
    }

    fun e(tag: String?, message: String?) {
        if (!isDebug || message == null)
            return
        Log.e(tag ?: LOG, message)
    }

    fun w(tag: String?, message: String?) {
        if (!isDebug || message == null)
            return
        Log.w(tag ?: LOG, message)
    }

    fun i(tag: String?, message: String?) {
        if (!isDebug || message == null)
            return
        Log.i(tag ?: LOG, message)
    }

    fun w(tag: String?, th: Throwable) {
        if (!isDebug)
            return
        Log.w(tag ?: LOG, "exception", th)
    }

}
