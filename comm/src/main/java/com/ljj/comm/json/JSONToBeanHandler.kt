package com.ljj.comm.json

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONException
import com.alibaba.fastjson.JSONObject

/**
 * Created by lijunjie on 2017/12/21.
 */
object JSONToBeanHandler {

    /**
     * 数据源转化为 对象bean
     *
     * @param data
     * @param cls
     * @return
     * @throws JSONFormatException
     */
    @Throws(JSONFormatException::class)
    fun <T> fromJsonString(data: String, cls: Class<T>): T? {
        try {
            return JSON.parseObject(data, cls)
        } catch (e: JSONException) {
            e?.printStackTrace()
            throw JSONFormatException("json format to " + cls.name + " exception :" + data, e)
        }

    }

    /**
     * 对象bean转化为json字符串
     *
     * @param value
     * @return
     * @throws JSONFormatException
     */
    @Throws(JSONFormatException::class)
    fun toJsonString(value: Any): String {
        try {
            return JSON.toJSONString(value)
        } catch (e: JSONException) {
            throw JSONFormatException(value.javaClass.name + " to json exception", e)
        }

    }

    @Throws(JSONFormatException::class)
    fun <T> fromJSONObject(json: JSONObject, key: String?, cls: Class<T>): T {
        if (key == null) {
            throw JSONFormatException("key is not null")
        }
        try {
            return json.getObject(key, cls)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw JSONFormatException("json format to " + cls.name + " exception :" + e.message)
        }

    }

}
