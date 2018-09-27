package com.ljj.kotlinsimple.page.contract

import com.ljj.comm.mvp.IViewDelegate

interface LoadDataContract {

    interface ViewDelegate : IViewDelegate {

        /**
         * 完成数据载入回调
         */
        fun doLoadDataCompleted()

        /**
         * 显示数据载入进度回调
         */
        fun doLoadingProgress(index: Int, count: Int)
    }

    interface Presenter {

        /**
         * 载入初始化数据
         */
        fun loadData()

        /**
         * 进入主页
         */
        fun toHomePage()
    }

}