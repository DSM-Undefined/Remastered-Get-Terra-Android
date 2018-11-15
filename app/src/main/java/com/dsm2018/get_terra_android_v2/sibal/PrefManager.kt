package com.dsm2018.get_terra_android_v2.sibal

import android.content.Context
import android.content.SharedPreferences

class PrefManager {
    private fun getPref(context: Context): SharedPreferences {
        return context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String, isAccess: Boolean = true) {
        getPref(context).edit().let {
            it.putString(getKey(isAccess), token)
            it.apply()
        }
    }

    fun removeToken(context: Context, isAccess: Boolean = true) {
        getPref(context).edit().let {
            it.remove(getKey(isAccess))
            it.apply()
        }
    }

    fun getToken(context: Context, getTokken: String, isAccess: Boolean = true): String {
        return "Bearer " + getPref(context).getString(getKey(isAccess), "")
    }

    private fun getKey(isAccess: Boolean): String = if (isAccess) "Access" else "Refresh"
}