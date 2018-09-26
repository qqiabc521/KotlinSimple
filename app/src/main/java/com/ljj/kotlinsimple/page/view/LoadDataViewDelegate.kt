package com.ljj.kotlinsimple.page.view

import android.widget.TextView

import com.ljj.comm.mvp.view.BaseViewDelegate
import com.ljj.kotlinsimple.R

class LoadDataViewDelegate : BaseViewDelegate() {
    private lateinit var hitTV: TextView

    override val rootLayoutId: Int
        get() = R.layout.activity_load_data

    override fun initWidget() {
        hitTV = get(R.id.load_data_hit)
    }

    /**
     * 完成初始化数据后回调
     */
    fun doLoadDataCompleted() {
        hitTV.text = "数据已准备完成"
    }

    /**
     * 载入进度更新
     * @param index
     * @param count
     */
    fun doLoadingProgress(index: Int, count: Int) {
        hitTV.text = "数据已载入第 $index 条，共计 $count 条"
    }
}
