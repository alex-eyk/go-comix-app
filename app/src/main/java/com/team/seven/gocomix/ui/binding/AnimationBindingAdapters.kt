package com.team.seven.gocomix.ui.binding

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.BindingAdapter

object AnimationBindingAdapters {

    private const val DURATION = 300L

    private const val ALPHA_VISIBLE = 1F
    private const val ALPHA_TRANSPARENT = 0.2F

    @JvmStatic
    @BindingAdapter("alphaOnLongPress")
    fun setAlphaOnLongPress(view: View, with: View) {
        view.setOnLongClickListener {
            if (view.alpha == 1.0F) {
                disappearance(view)
                disappearance(with)
            } else {
                appearance(view)
                appearance(with)
            }
            return@setOnLongClickListener true
        }
    }

    private fun appearance(view: View) {
        animate(view, ALPHA_TRANSPARENT, ALPHA_VISIBLE)
    }

    private fun disappearance(view: View) {
        animate(view, ALPHA_VISIBLE, ALPHA_TRANSPARENT)
    }

    private fun animate(view: View, from: Float, to: Float) {
        ObjectAnimator.ofFloat(view, View.ALPHA, from, to)
            .setDuration(DURATION)
            .setTimeInterpolator(AccelerateDecelerateInterpolator())
            .start()
    }
}

private fun ObjectAnimator.setTimeInterpolator(
    interpolator: TimeInterpolator
) = apply {
    this.interpolator = interpolator
}
