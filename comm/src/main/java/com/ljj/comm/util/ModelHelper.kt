package com.ljj.comm.util

import com.alibaba.android.arouter.facade.template.IProvider
import com.alibaba.android.arouter.launcher.ARouter
import java.util.*

object ModelHelper {

    private val providerCache = HashMap<String, IProvider>()

    fun <T : IProvider> getModel(t: Class<T>): T? {
        val tName = t.javaClass.name
        var provider: IProvider? = providerCache.get(tName)
        if (provider != null) {
            return provider as T?
        }
        provider = ARouter.getInstance().navigation(t)
        providerCache.put(tName, provider)
        return provider
    }
}
