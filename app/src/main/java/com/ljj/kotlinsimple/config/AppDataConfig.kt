package com.ljj.kotlinsimple.config


import com.ljj.comm.entity.Relationship
import com.ljj.kotlinsimple.feed.bean.FeedBean
import com.ljj.kotlinsimple.user.bean.UserBean
import java.util.*

/**
 * Created by lijunjie on 2017/12/28.
 */

object AppDataConfig {

    fun createUsers(count: Int): List<UserBean> {
        val userBeans = ArrayList<UserBean>(count)

        for (i in 1..count) {
            val userBean = UserBean()
            userBean.age = (Math.random() * 100).toInt()
            userBean.name = "name$i"
            userBean.email = "email$i"
            userBean.phoneNumber = "phoneNumber$i"
            userBean.description = "我是傻瓜$i"
            if (i == 1) {
                userBean.relationship = Relationship.OWNER
            } else if (i % 3 == 0) {
                userBean.relationship = Relationship.FOLLOWED
            } else {
                userBean.relationship = Relationship.DEFAULT
            }

            userBeans.add(userBean)
        }

        return userBeans
    }

    fun createFeeds(count: Int): List<FeedBean> {
        val feedBeans = ArrayList<FeedBean>(count)

        for (i in 1..count) {
            val feedBean = FeedBean()
            feedBean.title = "Kotlin TheMVP的第" + i + "条feed"
            feedBean.content = "开发高质量应用，离不开良好的框架设计和团队所有成员的共同努力，我是" + i + "条feed"

            feedBeans.add(feedBean)
        }

        return feedBeans
    }
}
