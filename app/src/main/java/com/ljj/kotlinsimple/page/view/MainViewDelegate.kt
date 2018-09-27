package com.ljj.kotlinsimple.page.view

import com.ljj.comm.mvp.view.BaseViewDelegate
import com.ljj.kotlinsimple.R
import com.ljj.kotlinsimple.page.contract.MainContract

class MainViewDelegate : BaseViewDelegate(),MainContract.View {

    override val rootLayoutId: Int
        get() = R.layout.activity_main

    override fun initWidget() {

    }

    override fun enableSwipeBack(): Boolean {
        return false
    }
}
