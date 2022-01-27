package com.zuluft.registration

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.zuluft.api.extensions.transitionInflater
import com.zuluft.controllers.BackgroundController
import com.zuluft.di.REGISTRATION_MODULE
import com.zuluft.koin_api.controllers.fragments.BaseFeatureFragment
import com.zuluft.registration.databinding.FragmentRegistrationBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class RegistrationFragment :
    BaseFeatureFragment<RegistrationViewState, RegistrationViewModel>() {

    private val viewModel by viewModel<RegistrationViewModel>()
    private lateinit var binding: FragmentRegistrationBinding

    private lateinit var backgroundController: BackgroundController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        backgroundController = context as BackgroundController
    }

    override fun prepareView() {

    }

    override fun onResume() {
        super.onResume()
        backgroundController.loadBackground(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_image_3
            )!!
        )
    }

    override fun reflectState(state: RegistrationViewState) {

    }

    override fun provideViewModel(): RegistrationViewModel {
        return viewModel
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        return FragmentRegistrationBinding.inflate(inflater, container, false)
            .also {
                binding = it
            }.root
    }


    override fun provideModulesToLoad(): List<Module> {
        return listOf(REGISTRATION_MODULE)
    }

    override fun provideModulesToUnload(): List<Module> {
        return listOf(REGISTRATION_MODULE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTransitions()
    }

    private fun setupTransitions() {
        enterTransition = transitionInflater().inflateTransition(
            android.R.transition.slide_right
        )
        exitTransition = transitionInflater().inflateTransition(
            android.R.transition.slide_left
        )
        returnTransition = transitionInflater()
            .inflateTransition(android.R.transition.slide_right)
        sharedElementEnterTransition = transitionInflater().inflateTransition(
            R.transition.default_transition
        )
        sharedElementReturnTransition = transitionInflater().inflateTransition(
            R.transition.default_transition
        ).apply {
            startDelay = RETURN_TRANSACTION_START_DELAY
        }
        allowEnterTransitionOverlap = false
        allowReturnTransitionOverlap = false
    }

    companion object {
        private const val RETURN_TRANSACTION_START_DELAY = 800L
    }
}