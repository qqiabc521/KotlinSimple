package com.ljj.comm.db

import android.content.Context

import com.alibaba.android.arouter.facade.annotation.Route
import com.ljj.comm.greendao.DaoSession
import com.ljj.comm.greendao.FeedEntityDao
import com.ljj.comm.greendao.UserEntityDao

@Route(path = "/base/db/service")
class DbServiceImpl : DbService {

    private val daoSession: DaoSession = DbFactory.instance.getDaoSession()

    override val feedEntityDao: FeedEntityDao
        get() = daoSession.feedEntityDao

    override val userEntityDao: UserEntityDao
        get() = daoSession.userEntityDao

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    override fun init(context: Context) {

    }

}
