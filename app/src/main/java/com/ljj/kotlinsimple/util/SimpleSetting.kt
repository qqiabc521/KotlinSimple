package com.ljj.kotlinsimple.util

import com.ljj.comm.util.PrefsManager

/**
 * Created by Lijj on 17/2/21.
 */

object SimpleSetting {

    private const val SETTING = "setting"

    private const val LOADED_DATA = "loaded_data"

    var isLoadedData: Boolean
        get() = PrefsManager.getBoolean(SETTING, LOADED_DATA, false)
        set(flag) = PrefsManager.putBoolean(SETTING, LOADED_DATA, flag)


}
