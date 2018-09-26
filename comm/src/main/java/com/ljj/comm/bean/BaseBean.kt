package com.ljj.comm.bean


import com.ljj.comm.json.JSONFormatException
import com.ljj.comm.json.JSONToBeanHandler

open class BaseBean {

    override fun toString(): String {
        try {
            return JSONToBeanHandler.toJsonString(this)
        } catch (e: JSONFormatException) {
            e.printStackTrace()
        }

        return super.toString()
    }
}
