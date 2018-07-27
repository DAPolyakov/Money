package yandexschool.dmpolyakov.money.utils

import android.content.Context
import android.net.ConnectivityManager


fun isLostNetworkConnection(context: Context): Boolean {
    try {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                ?: return true

        val activeNetwork = cm.activeNetworkInfo
        return if (activeNetwork != null) {
            activeNetwork.type != ConnectivityManager.TYPE_WIFI && activeNetwork.type != ConnectivityManager.TYPE_MOBILE
        } else true
    } catch (e: Exception) {
        return true
    }
}