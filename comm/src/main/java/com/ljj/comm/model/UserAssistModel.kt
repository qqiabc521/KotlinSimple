package com.ljj.comm.model

import com.alibaba.android.arouter.facade.template.IProvider
import com.ljj.comm.entity.Relationship
import com.ljj.comm.mvp.RequestCallBack

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface UserAssistModel : IProvider {

    /**
     * 更新用户关系
     *
     * @param userId
     * @param relationship
     * @param callBack
     * @return
     */
    fun updateUserRelation(userId: Long, relationship: Relationship, callBack: RequestCallBack<Boolean>): Disposable

    /**
     * 更新用户关系
     *
     * @param userId
     * @param relationship
     * @return
     */
    fun updateUserRelation(userId: Long, relationship: Relationship): Observable<Boolean>

}
