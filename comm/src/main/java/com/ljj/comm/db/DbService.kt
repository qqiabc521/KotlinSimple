package com.ljj.comm.db

import com.alibaba.android.arouter.facade.template.IProvider
import com.ljj.comm.greendao.FeedEntityDao
import com.ljj.comm.greendao.UserEntityDao

interface DbService : IProvider {

    val feedEntityDao: FeedEntityDao

    val userEntityDao: UserEntityDao
}
