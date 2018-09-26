package com.ljj.comm.util

import com.ljj.comm.BaseApplication
import com.ljj.comm.json.JSONFormatException
import com.ljj.comm.json.JSONToBeanHandler

object PrefsManager {

    private val context: BaseApplication?
        get() = BaseApplication.instance

    fun getInt(tbl: String, key: String, def: Int): Int {
        if (context == null) {
            return def
        }
        val prefs = context!!.getAppSharedPreferences(tbl)
        return prefs.getInt(key, def)
    }

    fun getLong(tbl: String, key: String, def: Long): Long {
        if (context == null) {
            return def
        }
        val prefs = context!!.getAppSharedPreferences(tbl)
        return prefs.getLong(key, def)
    }

    fun getString(tbl: String, key: String, def: String?): String? {
        if (context == null) {
            return def
        }
        val prefs = context!!.getAppSharedPreferences(tbl)
        return prefs.getString(key, def)
    }

    fun getBoolean(tbl: String, key: String, def: Boolean): Boolean {
        if (context == null) {
            return def
        }
        val prefs = context!!.getAppSharedPreferences(tbl)
        return prefs.getBoolean(key, def)
    }

    fun getFloat(tbl: String, key: String, def: Float): Float {
        if (context == null) {
            return def
        }

        val prefs = context!!.getAppSharedPreferences(tbl)
        return prefs.getFloat(key, def)
    }

    fun <T> getObject(tbl: String, key: String, cls: Class<T>): T? {
        if (context == null) {
            return null
        }
        val content = getString(tbl, key, null) ?: return null
        try {
            return JSONToBeanHandler.fromJsonString(content, cls)
        } catch (e: JSONFormatException) {
            e.printStackTrace()
        }

        return null
    }

    fun putObject(tbl: String, key: String, value: Any?) {
        if (context == null) {
            return
        }
        var content: String? = null
        if (value != null) {
            try {
                content = JSONToBeanHandler.toJsonString(value)
            } catch (e: JSONFormatException) {
                e.printStackTrace()
            }

        }
        putString(tbl, key, content)
    }

    fun putInt(tbl: String, key: String, value: Int) {
        if (context == null) {
            return
        }
        val prefs = context!!.getAppSharedPreferences(tbl)
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun putLong(tbl: String, key: String, value: Long, immediately: Boolean) {
        if (context == null) {
            return
        }
        val prefs = context!!.getAppSharedPreferences(tbl)
        val editor = prefs.edit()
        editor.putLong(key, value)
        if (!immediately) {
            editor.apply()
        } else {
            editor.commit()
        }
    }

    fun putString(tbl: String, key: String, value: String?) {
        if (context == null) {
            return
        }
        val prefs = context!!.getAppSharedPreferences(tbl)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun putBoolean(tbl: String, key: String, value: Boolean) {
        if (context == null) {
            return
        }
        val prefs = context!!.getAppSharedPreferences(tbl)
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun putFloat(tbl: String, key: String, value: Float) {
        if (context == null) {
            return
        }
        val prefs = context!!.getAppSharedPreferences(tbl)
        val editor = prefs.edit()
        editor.putFloat(key, value)
        editor.apply()
    }
}
