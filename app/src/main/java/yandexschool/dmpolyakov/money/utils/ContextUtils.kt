package yandexschool.dmpolyakov.money.utils

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat


fun Context.getCompatColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}