package com.zuluft.ui_components.logo

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.util.AttributeSet
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import com.zuluft.base.BaseComponent
import com.zuluft.ui_components.R

class LogoView : BaseComponent {

    private val ivBreaking: ImageView
    private val ivBad: ImageView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr

    ) {
        inflate(context, R.layout.layout_logo, this)
        ivBreaking = findViewById(R.id.ivBreaking)
        ivBad = findViewById(R.id.ivBad)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LogoView)
        val setupForAnim = typedArray.getBoolean(R.styleable.LogoView_setUpForAnim, false)
        typedArray.recycle()
        if (setupForAnim) {
            ivBreaking.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.breaking_anim
                )
            )
            ivBad.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.bad_anim
                )
            )
            ivBreaking.translationX = -OFFSET_TRANSLATION_X
            ivBad.translationX = OFFSET_TRANSLATION_X
        }
    }

    private fun createFirstScene(): Animator {
        return ObjectAnimator.ofFloat(ivBreaking, TRANSLATION_X, NORMAL_TRANSLATION_X)
            .apply {
                duration = FIRST_SCENE_DURATION
                interpolator = DecelerateInterpolator()
            }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        val breakingAnim =
            ContextCompat.getDrawable(context, R.drawable.breaking_anim) as AnimatedVectorDrawable
        val badAnim =
            ContextCompat.getDrawable(context, R.drawable.bad_anim) as AnimatedVectorDrawable
        ivBreaking.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_breaking))
        ivBad.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_bad))
        breakingAnim.reset()
        badAnim.reset()
    }


    private fun createSecondScene(): Animator {
        return ObjectAnimator.ofFloat(ivBad, TRANSLATION_X, NORMAL_TRANSLATION_X)
            .apply {
                duration = SECOND_SCENE_DURATION
                interpolator = AnticipateOvershootInterpolator()
            }
    }

    fun startAnim(endAction: (() -> Unit)? = null) {
        post {
            AnimatorSet()
                .apply {
                    playTogether(createFirstScene(), createSecondScene())
                    doOnEnd {
                        (ivBreaking.drawable as AnimatedVectorDrawable).start()
                        (ivBad.drawable as AnimatedVectorDrawable).start()
                        endAction?.invoke()
                    }
                }
                .start()
        }
    }

    companion object {
        private const val NORMAL_TRANSLATION_X = 0f
        private const val OFFSET_TRANSLATION_X = 1500f
        private const val FIRST_SCENE_DURATION = 3000L
        private const val SECOND_SCENE_DURATION = 3000L
    }
}