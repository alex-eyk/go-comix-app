package com.team.seven.gocomix.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.drawable.shapes.Shape
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.graphics.drawable.DrawableWrapper
import androidx.appcompat.widget.AppCompatRatingBar

/**
 * https://github.com/Malligan/RatingBarSvg
 * @author Malligan
 */
class VectorRatingBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatRatingBar(context, attrs, defStyleAttr) {

    private var sampleTile: Bitmap? = null

    companion object {
        private const val CORNERS_SIZE = 5f
        private const val CORNERS_NUMBER = 8
    }

    private val drawableShape: Shape
        get() {
            val roundedCorners = FloatArray(CORNERS_NUMBER) { CORNERS_SIZE }
            return RoundRectShape(roundedCorners, null, null)
        }

    init {
        val drawable = tileify(progressDrawable, false) as LayerDrawable
        progressDrawable = drawable
    }

    @SuppressLint("RestrictedApi")
    private fun tileify(drawable: Drawable, clip: Boolean): Drawable {
        return when (drawable) {
            is DrawableWrapper -> {
                var inner: Drawable? = drawable.wrappedDrawable
                if (inner != null) {
                    inner = tileify(inner, clip)
                    drawable.wrappedDrawable = inner
                }
                drawable
            }
            is LayerDrawable -> {
                val numberOfLayers = drawable.numberOfLayers
                val outDrawables = arrayOfNulls<Drawable>(numberOfLayers)
                for (i in 0 until numberOfLayers) {
                    val id = drawable.getId(i)
                    outDrawables[i] = tileify(
                        drawable.getDrawable(i),
                        id == android.R.id.progress || id == android.R.id.secondaryProgress
                    )
                }
                val newBg = LayerDrawable(outDrawables)
                for (i in 0 until numberOfLayers) {
                    newBg.setId(i, drawable.getId(i))
                }
                newBg
            }
            is BitmapDrawable -> {
                val tileBitmap = drawable.bitmap
                if (sampleTile == null) {
                    sampleTile = tileBitmap
                }
                val shapeDrawable = ShapeDrawable(drawableShape)
                val bitmapShader = BitmapShader(
                    tileBitmap,
                    Shader.TileMode.REPEAT,
                    Shader.TileMode.CLAMP
                )
                shapeDrawable.paint.shader = bitmapShader
                shapeDrawable.paint.colorFilter = drawable.paint.colorFilter
                if (clip)
                    ClipDrawable(
                        shapeDrawable,
                        Gravity.START,
                        ClipDrawable.HORIZONTAL
                    )
                else
                    shapeDrawable
            }
            else -> tileify(getBitmapDrawableFromVectorDrawable(drawable), clip)
        }
    }

    private fun getBitmapDrawableFromVectorDrawable(drawable: Drawable): BitmapDrawable {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth + (2).toInt(),
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return BitmapDrawable(resources, bitmap)
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (sampleTile != null) {
            val width = sampleTile!!.width * numStars
            setMeasuredDimension(
                resolveSizeAndState(width, widthMeasureSpec, 0),
                measuredHeight
            )
        }
    }
}
