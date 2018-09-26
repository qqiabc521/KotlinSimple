package com.ljj.kotlinsimple.page.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

import com.ljj.comm.mvp.view.BaseViewDelegate
import com.ljj.kotlinsimple.R

class MainViewDelegate : BaseViewDelegate() {

    override val rootLayoutId: Int
        get() = R.layout.activity_main

    override fun create(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        super.create(inflater, container, savedInstanceState)
    }

    override fun initWidget() {

    }
}
