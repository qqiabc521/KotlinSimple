package com.ljj.comm.mvp

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface IViewDelegate {

    val optionsMenuId: Int

    val toolbar: Toolbar?

    val rootView: View

    fun create(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)

    fun postCreate()

    fun initWidget()

    fun destory()

    fun showLoadingView()

    fun hideLoadView()

    fun showNofityMessage(message: String)

    fun setOnClickListenter(onClickListenter: View.OnClickListener, vararg ids: Int)

}
