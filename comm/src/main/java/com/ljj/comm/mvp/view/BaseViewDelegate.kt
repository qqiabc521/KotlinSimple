package com.ljj.comm.mvp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ljj.comm.mvp.IViewDelegate
import com.ljj.comm.util.AppLog
import com.ljj.comm.util.LoadingHelper

abstract class BaseViewDelegate : IViewDelegate {
    override lateinit var rootView: View
        protected set
    private var destoryed = false

    abstract val rootLayoutId: Int

    override val optionsMenuId: Int
        get() = 0

    override val toolbar: Toolbar?
        get() = null

    private val tag: String
        get() = this.javaClass.simpleName

    override fun create(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        val rooLayoutId = rootLayoutId
        rootView = inflater.inflate(rooLayoutId, container, false)
    }

    override fun destory() {
        destoryed = true
    }

    override fun showLoadingView() {
        if (destoryed) {
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
        AppLog.e(tag, message)
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
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

    fun setOnClickListenter(onClickListenter: View.OnClickListener, vararg ids: Int) {
        if (ids == null) {
            return
        }
        for (id in ids) {
            get<View>(id).setOnClickListener(onClickListenter)
        }
    }

    fun <T : AppCompatActivity> getActivity(): T {
        return rootView.context as T
    }


}
