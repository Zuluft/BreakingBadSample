package com.zuluft.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import com.zuluft.ui_components.R
import com.zuluft.ui_components.databinding.FragmentLogoControllerBinding

class LogoControllerImpl :
    Fragment(),
    LogoController {

    private lateinit var binding: FragmentLogoControllerBinding

    private var animSceneListener: (() -> Unit)? = null

    fun setAnimSceneListener(animSceneListener: () -> Unit) {
        this.animSceneListener = animSceneListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLogoControllerBinding.inflate(inflater, container!!, false)
            .also {
                binding = it
            }.root
    }

    private fun setupTransition() {
        TransitionManager.beginDelayedTransition(binding.root,
            TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.default_transition)
                .apply {
                    startDelay = LOGO_ANIM_START_DELAY
                    duration = LOGO_ANIM_DURATION
                    interpolator = AnticipateOvershootInterpolator()
                    addListener(object : Transition.TransitionListener {
                        override fun onTransitionStart(transition: Transition) {

                        }

                        override fun onTransitionEnd(transition: Transition) {
                            animSceneListener?.invoke()
                        }

                        override fun onTransitionCancel(transition: Transition) {

                        }

                        override fun onTransitionPause(transition: Transition) {

                        }

                        override fun onTransitionResume(transition: Transition) {

                        }

                    })
                })
    }

    override fun startContentAnim() {
        binding.logo.startAnim {
            startTranslationAnim()
        }
    }


    private fun startTranslationAnim() {
        setupTransition()
        ConstraintSet()
            .apply {
                clone(binding.root)
                clear(binding.logo.id, ConstraintSet.BOTTOM)
                connect(
                    binding.logo.id,
                    ConstraintSet.TOP,
                    R.id.topGuideline,
                    ConstraintSet.BOTTOM
                )
                applyTo(binding.root)
            }
    }

    companion object {
        private const val LOGO_ANIM_START_DELAY = 1000L
        private const val LOGO_ANIM_DURATION = 3000L
    }
}