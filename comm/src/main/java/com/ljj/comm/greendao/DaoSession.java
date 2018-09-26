package com.ljj.comm.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.ljj.comm.entity.FeedEntity;
import com.ljj.comm.entity.UserEntity;

import com.ljj.comm.greendao.FeedEntityDao;
import com.ljj.comm.greendao.UserEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig feedEntityDaoConfig;
    private final DaoConfig userEntityDaoConfig;

    private final FeedEntityDao feedEntityDao;
    private final UserEntityDao userEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        feedEntityDaoConfig = daoConfigMap.get(FeedEntityDao.class).clone();
        feedEntityDaoConfig.initIdentityScope(type);

        userEntityDaoConfig = daoConfigMap.get(UserEntityDao.class).clone();
        userEntityDaoConfig.initIdentityScope(type);

        feedEntityDao = new FeedEntityDao(feedEntityDaoConfig, this);
        userEntityDao = new UserEntityDao(userEntityDaoConfig, this);

        registerDao(FeedEntity.class, feedEntityDao);
        registerDao(UserEntity.class, userEntityDao);
    }
    
    public void clear() {
        feedEntityDaoConfig.clearIdentityScope();
        userEntityDaoConfig.clearIdentityScope();
    }

    public FeedEntityDao getFeedEntityDao() {
        return feedEntityDao;
    }

    public UserEntityDao getUserEntityDao() {
        return userEntityDao;
    }

}
