package com.zuluft.intro.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zuluft.api.extensions.transitionInflater
import com.zuluft.controllers.BackgroundController
import com.zuluft.controllers.LogoController
import com.zuluft.controllers.LogoControllerHolder
import com.zuluft.intro.R
import com.zuluft.intro.databinding.FragmentIntroBinding
import com.zuluft.intro.di.INTRO_MODULE
import com.zuluft.koin_api.controllers.fragments.BaseFeatureFragment
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class IntroFragment : BaseFeatureFragment<IntroViewState, IntroViewModel>() {

    private val viewModel by viewModel<IntroViewModel>()

    private val logoController by inject<LogoController>()

    private lateinit var binding: FragmentIntroBinding

    private lateinit var logoControllerHolder: LogoControllerHolder

    private lateinit var backgroundController: BackgroundController


    override fun onAttach(context: Context) {
        super.onAttach(context)
        logoControllerHolder = context as LogoControllerHolder
        backgroundController = context as BackgroundController
    }

    override fun prepareView() {
        logoControllerHolder.attachController(logoController) {
            getViewModel()?.introSceneFinishedIntent()
        }
    }

    override fun onResume() {
        super.onResume()
        startIntroScene()
    }

    private fun startIntroScene() {
        logoController.startContentAnim()
        backgroundController.loadBackground(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_image_1
            )!!
        )
    }


    override fun reflectState(state: IntroViewState) {
        with(state) {
            goToLogin?.getValue()?.also {
                goToLoginScreen()
            }

        }
    }

    private fun goToLoginScreen() {
        findNavController().navigate(
            IntroFragmentDirections.actionIntroFragmentToFeatureLogin()
        )
    }

    override fun provideViewModel(): IntroViewModel {
        return viewModel
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        return FragmentIntroBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun provideModulesToLoad(): List<Module> {
        return listOf(INTRO_MODULE)
    }

    override fun provideModulesToUnload(): List<Module> {
        return listOf(INTRO_MODULE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTransactions()
    }

    private fun setupTransactions() {
        enterTransition = transitionInflater()
            .inflateTransition(android.R.transition.fade)
            .apply {
                duration = TRANSITION_ANIM_DURATION
            }
        exitTransition = transitionInflater()
            .inflateTransition(android.R.transition.fade)
            .apply {
                duration = TRANSITION_ANIM_DURATION
            }
    }

    companion object {
        private const val TRANSITION_ANIM_DURATION = 2000L
    }
}