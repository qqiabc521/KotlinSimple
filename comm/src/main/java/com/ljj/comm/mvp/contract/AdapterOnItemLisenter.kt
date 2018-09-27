package com.ljj.comm.mvp.contract

interface AdapterOnItemLisenter<T> {

    /**
     * 点击item回调
     */
    fun onClickItemLisenter(bean: T, position: Int)

}