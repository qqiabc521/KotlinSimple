package com.ljj.kotlinsimple

import android.content.Context
import android.support.multidex.MultiDex

import com.ljj.comm.BaseApplication

class MainApplication : BaseApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

}
