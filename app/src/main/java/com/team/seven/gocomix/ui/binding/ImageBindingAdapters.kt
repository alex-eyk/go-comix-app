package com.team.seven.gocomix.ui.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import jp.wasabeef.glide.transformations.BlurTransformation

object ImageBindingAdapters {

    private const val BLUR_SMALL_RADIUS = 25
    private const val BLUR_BG_RADIUS = 50
    private const val BLUR_SAMPLING = 3

    @JvmStatic
    @BindingAdapter(
        value = [
            "image",
            "previewImage"
        ],
        requireAll = true
    )
    fun setImage(
        imageView: ImageView,
        imageUrl: String,
        previewImageUrl: String
    ) {
        baseGlideImageRequest(imageView, imageUrl)
            .thumbnail(
                baseGlideImageRequest(imageView, previewImageUrl)
                    .transform(BlurTransformation(BLUR_SMALL_RADIUS, BLUR_SAMPLING))
            )
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
                    BlurTransformation(BLUR_BG_RADIUS, BLUR_SAMPLING)
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
}
