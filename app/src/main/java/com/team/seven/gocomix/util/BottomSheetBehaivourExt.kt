package com.team.seven.gocomix.util

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

fun <V : View> BottomSheetBehavior<V>.hide() {
    this.state = BottomSheetBehavior.STATE_HIDDEN
}

fun <V : View> BottomSheetBehavior<V>.expand() {
    this.state = BottomSheetBehavior.STATE_EXPANDED
}

fun <V : View> BottomSheetBehavior<V>.setOnStateChange(
    callback: ((sheet: View, state: Int) -> Unit)
) {
    this.addBottomSheetCallback(
        object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                callback.invoke(bottomSheet, newState)
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        }
    )
}

fun <V : View> BottomSheetBehavior<V>.setOnSlide(
    callback: ((sheet: View, slideOffset: Float) -> Unit)
) {
    this.addBottomSheetCallback(
        object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                callback.invoke(bottomSheet, slideOffset)
            }
        }
    )
}
