package com.team.seven.gocomix.ui.binding

import android.view.View
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

    @JvmStatic
    @BindingAdapter("onLongClick")
    fun setLongClickListener(
        view: View,
        listener: () -> Unit
    ) {
        view.setOnLongClickListener {
            listener.invoke()
            return@setOnLongClickListener true
        }
    }
}
