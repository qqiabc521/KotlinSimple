package com.ljj.comm

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import com.alibaba.android.arouter.launcher.ARouter
import com.ljj.comm.config.Constants

open class BaseApplication : Application() {

    val isLoggable: Boolean
        get() = Constants.DEBUG

    override fun onCreate() {
        super.onCreate()
        instance = this

        if(isLoggable) {
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    fun getAppSharedPreferences(tbl: String): SharedPreferences {
        return getSharedPreferences(tbl, Context.MODE_PRIVATE)
    }

    companion object {

        lateinit var instance: BaseApplication
            private set
    }
}
