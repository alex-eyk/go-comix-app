package com.team.seven.gocomix.ui.binding

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.team.seven.gocomix.R
import com.team.seven.gocomix.data.entity.Quality
import com.team.seven.gocomix.di.NetworkModule
import com.team.seven.gocomix.util.dimenInDp
import com.team.seven.gocomix.util.themeColor
import jp.wasabeef.glide.transformations.BlurTransformation

object ImageBindingAdapters {

    private const val BLUR_RADIUS = 50
    private const val BLUR_SAMPLING = 3

    @JvmStatic
    @BindingAdapter(
        value = [
            "image",
            "quality"
        ],
        requireAll = true
    )
    fun setImage(imageView: ImageView, id: Int, quality: Quality) {
        val imageUrl = "${NetworkModule.COMICS_SERVER_URL}/comix/image/$id" +
                "?quality=${quality.ordinal}"
        baseGlideImageRequest(imageView, imageUrl)
            .placeholder(circularProgressDrawable(imageView.context))
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
            setColorSchemeColors(progressColor)
            start()
        }
        return progressDrawable
    }

    private fun centerBlurTransform(): Transformation<Bitmap> {
        return MultiTransformation(
            CenterCrop(),
            BlurTransformation(BLUR_RADIUS, BLUR_SAMPLING)
        )
    }
}
