package com.ljj.kotlinsimple.user.model

import com.alibaba.android.arouter.facade.template.IProvider
import com.ljj.comm.entity.UserEntity
import com.ljj.comm.mvp.RequestCallBack

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface UserModel : IProvider {

    /**
     * 添加一条User
     *
     * @param userEntity
     * @param callBack
     * @return
     */
    fun saveUser(userEntity: UserEntity, callBack: RequestCallBack<Long>): Disposable

    /**
     * 添加一条User
     *
     * @param userEntity
     * @return
     */
    fun saveUser(userEntity: UserEntity): Observable<Long>

    /**
     * 添加一组User
     *
     * @param userEntitys
     * @param callBack
     * @return
     */
    fun saveUsers(userEntitys: List<UserEntity>, callBack: RequestCallBack<Boolean>): Disposable

    /**
     * 添加一组User
     *
     * @param userEntitys
     * @return
     */
    fun saveUsers(userEntitys: List<UserEntity>): Observable<Boolean>

    /**
     * 获得用户详情 by userId
     * @param userId
     * @param callBack
     * @return
     */
    fun getUser(userId: Long, callBack: RequestCallBack<UserEntity>): Disposable

    /**
     * 获得用户详情 by userId
     * @param userId
     * @return
     */
    fun getUser(userId: Long): Observable<UserEntity>

    /**
     * 更新用户信息
     * @param userEntity
     * @param callBack
     * @return
     */
    fun updateUser(userEntity: UserEntity, callBack: RequestCallBack<Boolean>): Disposable

    /**
     * 更新用户信息
     * @param userEntity
     * @return
     */
    fun updateUser(userEntity: UserEntity): Observable<Boolean>

}
