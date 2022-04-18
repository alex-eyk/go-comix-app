package com.team.seven.gocomix.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("clipToOutline")
    fun setClipToOutline(
        imageView: ImageView,
        clip: Boolean
    ) {
        imageView.clipToOutline = clip
    }
}
