package com.ljj.comm.util

import android.content.Context
import android.util.SparseArray

import com.ljj.comm.R
import com.ljj.comm.widget.LoadingDialog

import java.lang.ref.WeakReference

object LoadingHelper {

    private val loadingCaches = SparseArray<WeakReference<LoadingDialog>>()

    fun create(context: Context, progressFlag: Boolean): LoadingDialog {
        var loadingDialog = getCache(context)
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(context, R.style.LoadingDialog, progressFlag)
            loadingCaches.put(context.hashCode(), WeakReference(loadingDialog))
        }

        return loadingDialog
    }

    fun hideLoading(context: Context): Boolean {
        val loadingDialog = getCache(context)
        if (loadingDialog != null) {
            loadingDialog.cancel()
            return true
        }
        return false
    }

    private fun getCache(context: Context): LoadingDialog? {
        val loadingDialogRef = loadingCaches.get(context.hashCode())
        return loadingDialogRef?.get()
    }


}
