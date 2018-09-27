package com.ljj.kotlinsimple.page.contract

import com.ljj.comm.mvp.IViewDelegate

interface MainContract {

    interface View : IViewDelegate

    interface Prestenter {

        /**
         * 进入Feed列表页面
         */
        fun toFeedsPage()
    }
}