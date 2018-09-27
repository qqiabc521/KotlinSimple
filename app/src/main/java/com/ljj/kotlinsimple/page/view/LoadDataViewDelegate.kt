package com.ljj.kotlinsimple.page.view

import android.widget.TextView

import com.ljj.comm.mvp.view.BaseViewDelegate
import com.ljj.kotlinsimple.R
import com.ljj.kotlinsimple.page.contract.LoadDataContract

class LoadDataViewDelegate : BaseViewDelegate(), LoadDataContract.ViewDelegate {
    /**
     * 完成数据载入回调
     */
    override fun doLoadDataCompleted() {
        hitTV.text = "数据已准备完成"
    }

    /**
     * 显示数据载入进度回调
     */
    override fun doLoadingProgress(index: Int, count: Int) {
        hitTV.text = "数据已载入第 $index 条，共计 $count 条"
    }

    private lateinit var hitTV: TextView

    override val rootLayoutId: Int
        get() = R.layout.activity_load_data

    override fun initWidget() {
        hitTV = get(R.id.load_data_hit)
    }

}
