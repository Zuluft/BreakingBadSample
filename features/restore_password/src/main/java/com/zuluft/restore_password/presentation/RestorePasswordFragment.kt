package com.zuluft.restore_password.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.zuluft.api.extensions.transitionInflater
import com.zuluft.controllers.BackgroundController
import com.zuluft.koin_api.controllers.fragments.BaseFeatureFragment
import com.zuluft.restore_password.R
import com.zuluft.restore_password.databinding.FragmentRestorePasswordBinding
import com.zuluft.restore_password.di.RESTORE_PASSWORD_MODULE
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class RestorePasswordFragment :
    BaseFeatureFragment<RestorePasswordViewState, RestorePasswordViewModel>() {

    private val viewModel by viewModel<RestorePasswordViewModel>()
    private lateinit var binding: FragmentRestorePasswordBinding

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
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_image_1)!!
        )
    }

    override fun reflectState(state: RestorePasswordViewState) {

    }

    override fun provideViewModel(): RestorePasswordViewModel {
        return viewModel
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        return FragmentRestorePasswordBinding.inflate(inflater, container!!, false)
            .also {
                binding = it
            }.root
    }

    override fun provideModulesToLoad(): List<Module> {
        return listOf(RESTORE_PASSWORD_MODULE)
    }

    override fun provideModulesToUnload(): List<Module> {
        return listOf(RESTORE_PASSWORD_MODULE)
    }

    private fun setupTransitions() {
        enterTransition = transitionInflater().inflateTransition(
            android.R.transition.fade
        )
        exitTransition = transitionInflater().inflateTransition(
            android.R.transition.fade
        )
        returnTransition = transitionInflater()
            .inflateTransition(android.R.transition.fade)
        sharedElementEnterTransition = transitionInflater().inflateTransition(
            R.transition.default_transition
        )
        sharedElementReturnTransition = transitionInflater().inflateTransition(
            R.transition.default_transition
        )
        allowEnterTransitionOverlap = false
        allowReturnTransitionOverlap = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTransitions()
    }
}