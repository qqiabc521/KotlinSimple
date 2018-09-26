package com.ljj.comm.db

import com.ljj.comm.BaseApplication
import com.ljj.comm.greendao.DaoMaster
import com.ljj.comm.greendao.DaoSession

class DbFactory private constructor() {

    private val daoMaster: DaoMaster
    private lateinit var daoSession: DaoSession

    private object DbFactoryHolder {
        internal val INSTANCE = DbFactory()
    }

    init {
        val devOpenHelper = DaoMaster.DevOpenHelper(BaseApplication.instance, DATA_BASE_ANME)
        val db = devOpenHelper.writableDb
        daoMaster = DaoMaster(db)
        daoSession = daoMaster.newSession()
    }

//    fun create(): DaoSession {
//        if (daoSession == null) {
//            daoSession = daoMaster.newSession()
//        }
//        return daoSession
//    }

    fun getDaoSession(): DaoSession {
        return daoSession;
    }

    companion object {
        private val DATA_BASE_ANME = "fool_mvp.db"

        val instance: DbFactory
            get() = DbFactoryHolder.INSTANCE
    }
}
