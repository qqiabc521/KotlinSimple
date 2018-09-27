package com.ljj.kotlinsimple.user.page.contract

import com.ljj.comm.mvp.IViewDelegate
import com.ljj.kotlinsimple.user.bean.UserBean

interface UserDetailContract {

    interface View : IViewDelegate {
        /**
         * 显示user信息
         */
        fun doUserDetail(userBean: UserBean)

        /**
         * 更新关注UI
         */
        fun doFollowedResult()

        /**
         * 更新未关注UI
         */
        fun doUnFollowResult()
    }

    interface Presenter {
        /**
         * 请求用户详情
         */
        fun requestUserDetail(userId: Long)

        /**
         * 关注用户
         */
        fun followUser()

        /**
         * 取消关注用户
         */
        fun unFollowUser()

    }
}