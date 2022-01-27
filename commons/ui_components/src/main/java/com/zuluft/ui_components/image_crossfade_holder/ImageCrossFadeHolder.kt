package com.zuluft.ui_components.image_crossfade_holder

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.animation.doOnEnd
import com.zuluft.base.BaseComponent

class ImageCrossFadeHolder : BaseComponent {


    private var primaryImage: ImageView
    private var secondaryImage: ImageView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr

    ) {
        addView(createImageView().also {
            primaryImage = it
        })
        addView(createImageView().also {
            secondaryImage = it
        })
    }

    fun loadDrawable(drawable: Drawable) {
        if (primaryImage.drawable != drawable) {
            primaryImage.setImageDrawable(drawable)
            ValueAnimator.ofFloat(MIN_ALPHA, MAX_ALPHA)
                .apply {
                    addUpdateListener {
                        val alpha = it.animatedValue as Float
                        primaryImage.alpha = alpha
                        secondaryImage.alpha = MAX_ALPHA - alpha
                    }
                    duration = ANIM_DURATION
                    doOnEnd {
                        val tmp = primaryImage
                        primaryImage = secondaryImage
                        secondaryImage = tmp
                    }
                }.start()
        }

    }


    private fun createImageView(): ImageView {
        return AppCompatImageView(context)
            .apply {
                id = View.generateViewId()
                alpha = 0f
                layoutParams = ViewGroup.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
    }

    companion object {
        private const val ANIM_DURATION = 1000L
        private const val MIN_ALPHA = 0f
        private const val MAX_ALPHA = 1f
    }
}