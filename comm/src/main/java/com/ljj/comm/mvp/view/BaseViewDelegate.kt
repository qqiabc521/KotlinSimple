package com.ljj.comm.mvp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cxz.swipelibrary.SwipeBackActivityHelper
import com.cxz.swipelibrary.SwipeBackLayout
import com.ljj.comm.mvp.IViewDelegate
import com.ljj.comm.util.LoadingHelper

abstract class BaseViewDelegate : IViewDelegate {

    override lateinit var rootView: View
        protected set

    private var isViewDestory = false

    //根布局文件
    abstract val rootLayoutId: Int

    override val optionsMenuId: Int
        get() = 0

    override val toolbar: Toolbar?
        get() = null

    private val tag: String
        get() = this.javaClass.simpleName

    private var swipeBackHelper : SwipeBackActivityHelper? = null

    open fun enableSwipeBack() : Boolean = true

    override fun create(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        val layoutId = rootLayoutId
        rootView = inflater.inflate(layoutId, container, false)
        if(enableSwipeBack()){
            initSwipeBackHelper()
        }
    }

    override fun postCreate() {
        swipeBackHelper?.onPostCreate()
    }

    override fun destory() {
        isViewDestory = true
    }

    override fun showLoadingView() {
        if (isViewDestory) {
            return
        }
        val loadingDialog = LoadingHelper.create(getActivity(), false)
        loadingDialog.setCancelable(false)
        if (!loadingDialog.isShowing) {
            loadingDialog.show()
        }
    }

    override fun hideLoadView() {
        LoadingHelper.hideLoading(getActivity())
    }

    override fun showNofityMessage(message: String) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
    }

    override fun setOnClickListenter(onClickListenter: View.OnClickListener, vararg ids: Int) {
        if (ids == null) {
            return
        }
        for (id in ids) {
            get<View>(id).setOnClickListener(onClickListenter)
        }
    }

    protected fun setActionBarTitle(title: String) {
        if (toolbar != null) {
            toolbar!!.title = title
        } else if (getActivity<AppCompatActivity>().supportActionBar != null) {
            getActivity<AppCompatActivity>().supportActionBar!!.title = title
        }
    }

    fun <T : View> bindView(id: Int): T {
        return rootView.findViewById(id)
    }

    operator fun <T : View> get(id: Int): T {
        return bindView(id)
    }

    fun <T : AppCompatActivity> getActivity(): T {
        return rootView.context as T
    }

    private fun initSwipeBackHelper(){
        swipeBackHelper = SwipeBackActivityHelper(getActivity())
        swipeBackHelper?.onActivityCreate()
        getSwipeBackLayout()?.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)
    }

    private fun getSwipeBackLayout() : SwipeBackLayout? {
        return swipeBackHelper?.swipeBackLayout
    }

    protected fun setSwipeBackEnable(enable : Boolean){
        getSwipeBackLayout()?.setEnableGesture(enable)
    }

}
