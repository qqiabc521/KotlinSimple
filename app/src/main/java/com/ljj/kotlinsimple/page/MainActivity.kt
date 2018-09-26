package com.ljj.kotlinsimple.page

import android.os.Bundle
import android.view.View

import com.alibaba.android.arouter.launcher.ARouter
import com.ljj.comm.mvp.presenter.ActivityPresenter
import com.ljj.kotlinsimple.R
import com.ljj.kotlinsimple.page.view.MainViewDelegate

class MainActivity : ActivityPresenter<MainViewDelegate>(), View.OnClickListener {

    override val delegateClass: Class<MainViewDelegate>
        get() = MainViewDelegate::class.java

    override fun onCreateBefore(savedInstanceState: Bundle?) {}

    override fun onCreateAfter() {
        viewDelegate.setOnClickListenter(this, R.id.home_local_feeds)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.home_local_feeds -> ARouter.getInstance().build("/feed/activity/feeds").navigation(this)
        }
    }
}
