package com.team.seven.gocomix.ui.binding

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.BindingAdapter

object AnimationBindingAdapters {

    private const val DURATION = 300L

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
        animate(view, 0.2F, 1F)
    }

    private fun disappearance(view: View) {
        animate(view, 1F, 0.2F)
    }

    private fun animate(view: View, vararg values: Float) {
        ObjectAnimator.ofFloat(view, View.ALPHA, *values)
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
