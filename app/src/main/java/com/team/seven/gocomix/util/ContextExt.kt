package com.team.seven.gocomix.util

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes

@ColorInt
fun Context.themeColor(@AttrRes attrRes: Int): Int = TypedValue()
    .apply {
        theme.resolveAttribute(attrRes, this, true)
    }
    .data

fun Context.dimenInDp(@DimenRes dimenRes: Int): Float =
    (resources.getDimension(dimenRes) / resources.displayMetrics.density)
