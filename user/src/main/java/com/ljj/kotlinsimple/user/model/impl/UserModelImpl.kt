package com.ljj.kotlinsimple.user.model.impl

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ljj.comm.db.DbService
import com.ljj.comm.entity.Relationship
import com.ljj.comm.entity.UserEntity
import com.ljj.comm.greendao.UserEntityDao
import com.ljj.comm.model.UserAssistModel
import com.ljj.comm.mvp.RequestCallBack
import com.ljj.comm.util.RxUtils
import com.ljj.kotlinsimple.user.model.UserModel
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function

@Route(path = "/user/model/user")
class UserModelImpl : UserModel, UserAssistModel {

    private var userEntityDao: UserEntityDao = ARouter.getInstance().navigation(DbService::class.java).userEntityDao

    /**
     * 添加一条User
     *
     * @param userEntity
     * @param callBack
     * @return
     */
    override fun saveUser(userEntity: UserEntity, callBack: RequestCallBack<Long>): Disposable {
        return RxUtils.defaultCallback(saveUser(userEntity), callBack)
    }

    /**
     * 添加一条User
     *
     * @param userEntity
     * @return
     */
    override fun saveUser(userEntity: UserEntity): Observable<Long> {
        return Observable.just(userEntity).map { userEntity -> userEntityDao.insert(userEntity) }
    }

    /**
     * 添加一组User
     *
     * @param userEntities
     * @param callBack
     * @return
     */
    override fun saveUsers(userEntities: List<UserEntity>, callBack: RequestCallBack<Boolean>): Disposable {
        return RxUtils.defaultCallback(saveUsers(userEntities), callBack)
    }

    /**
     * 添加一组User
     *
     * @param userEntities
     * @return
     */
    override fun saveUsers(userEntities: List<UserEntity>): Observable<Boolean> {
        return Observable.just(userEntities).map { userEntities ->
            userEntityDao.insertInTx(userEntities)
            true
        }
    }

    /**
     * 获得用户详情 by userId
     *
     * @param userId
     * @param callBack
     * @return
     */
    override fun getUser(userId: Long, callBack: RequestCallBack<UserEntity>): Disposable {
        return RxUtils.defaultCallback(getUser(userId), callBack)
    }

    /**
     * 获得用户详情 by userId
     *
     * @param userId
     * @return
     */
    override fun getUser(userId: Long): Observable<UserEntity> {
        return Observable.just(userId).map(object : Function<Long, UserEntity> {

            @Throws(Exception::class)
            override fun apply(userId: Long): UserEntity {
                return userEntityDao.load(userId)
            }
        })
    }

    /**
     * 更新用户信息
     *
     * @param userEntity
     * @param callBack
     * @return
     */
    override fun updateUser(userEntity: UserEntity, callBack: RequestCallBack<Boolean>): Disposable {
        return RxUtils.defaultCallback(updateUser(userEntity), callBack)
    }

    /**
     * 更新用户信息
     *
     * @param userEntity
     * @return
     */
    override fun updateUser(userEntity: UserEntity): Observable<Boolean> {
        return Observable.just(userEntity).map { userEntity ->
            userEntityDao.update(userEntity)
            true
        }
    }

    /**
     * 更新用户关系
     *
     * @param userId
     * @param relationship
     * @param callBack
     * @return
     */
    override fun updateUserRelation(userId: Long, relationship: Relationship, callBack: RequestCallBack<Boolean>): Disposable {
        return RxUtils.defaultCallback(updateUserRelation(userId, relationship), callBack)
    }

    /**
     * 更新用户关系
     *
     * @param userId
     * @param relationship
     * @return
     */
    override fun updateUserRelation(userId: Long, relationship: Relationship): Observable<Boolean> {
        return Observable.just(userId).flatMap(object : Function<Long, ObservableSource<UserEntity>> {
            /**
             * Apply some calculation to the input value and return some other value.
             * @param userId the input value
             * @return the output value
             * @throws Exception on error
             */
            override fun apply(userId: Long): ObservableSource<UserEntity> {
                return getUser(userId!!)
            }

        }).doAfterNext { userEntity -> userEntity.relationShip = relationship }.concatMap { userEntity -> updateUser(userEntity) }
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    override fun init(context: Context) {

    }
}
