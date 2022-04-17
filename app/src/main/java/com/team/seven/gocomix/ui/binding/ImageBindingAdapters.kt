package com.team.seven.gocomix.ui.binding

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.team.seven.gocomix.R
import com.team.seven.gocomix.util.dimenInDp
import com.team.seven.gocomix.util.themeColor
import jp.wasabeef.glide.transformations.BlurTransformation

object ImageBindingAdapters {

    private const val BLUR_RADIUS = 50
    private const val BLUR_SAMPLING = 3

    @JvmStatic
    @BindingAdapter("image")
    fun setImage(
        imageView: ImageView,
        imageUrl: String
    ) {
        baseGlideImageRequest(imageView, imageUrl)
            .placeholder(circularProgressDrawable(imageView.context))
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("blurImage")
    fun setBlurImage(
        imageView: ImageView,
        url: String
    ) {
        baseGlideImageRequest(imageView, url)
            .transform(
                MultiTransformation(
                    CenterCrop(),
                    BlurTransformation(BLUR_RADIUS, BLUR_SAMPLING)
                )
            )
            .into(imageView)
    }

    private fun baseGlideImageRequest(
        imageView: ImageView,
        url: String
    ): RequestBuilder<Drawable> {
        return Glide.with(imageView)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
    }

    private fun circularProgressDrawable(
        context: Context
    ): CircularProgressDrawable {
        val progressColor = context.themeColor(
            com.google.android.material.R.attr.colorPrimaryVariant
        )
        val width = context.dimenInDp(R.dimen.circle_progress_width)
        val radius = context.dimenInDp(R.dimen.circle_progress_radius)
        val progressDrawable = CircularProgressDrawable(context).apply {
            strokeWidth = width
            centerRadius = radius
        }
        progressDrawable.setColorSchemeColors(progressColor)
        progressDrawable.start()
        return progressDrawable
    }
}
