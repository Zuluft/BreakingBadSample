package com.zuluft.login.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.zuluft.api.extensions.transitionInflater
import com.zuluft.controllers.BackgroundController
import com.zuluft.controllers.LogoControllerHolder
import com.zuluft.koin_api.controllers.fragments.BaseFeatureFragment
import com.zuluft.loaders.Loader
import com.zuluft.login.R
import com.zuluft.login.databinding.FragmentLoginBinding
import com.zuluft.login.di.LOGIN_MODULE
import com.zuluft.login.domain.models.LoginDataModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class LoginFragment : BaseFeatureFragment<LoginViewState, LoginViewModel>() {

    private val viewModel by viewModel<LoginViewModel>()
    private lateinit var binding: FragmentLoginBinding

    private lateinit var backgroundController: BackgroundController

    private lateinit var logoControllerHolder: LogoControllerHolder

    override fun onAttach(context: Context) {
        super.onAttach(context)
        backgroundController = context as BackgroundController
        logoControllerHolder = context as LogoControllerHolder
    }

    override fun prepareView() {
        setupIntents()
    }

    private fun setupIntents() {
        with(binding) {
            btnRegister.setOnClickListener {
                getViewModel()?.onRegisterClickIntent()
            }
            tfRestorePassword.setOnClickListener {
                getViewModel()?.onRestorePasswordClickIntent()
            }
            btnLogin.setOnClickListener {
                getViewModel()?.onLoginIntent(createLoginDataModel())
            }
        }

    }

    private fun createLoginDataModel(): LoginDataModel {
        val username = binding.ifUsername.text.toString()
        val password = binding.ifPassword.text.toString()
        return LoginDataModel(username, password)
    }

    private fun goToRestorePasswordScreen() {
        exitTransition = transitionInflater().inflateTransition(
            android.R.transition.fade
        )
        findNavController()
            .navigate(
                LoginFragmentDirections.actionLoginFragmentToFeatureRestorePassword(),
                FragmentNavigatorExtras(
                    binding.btnLogin to binding.btnLogin.transitionName,
                    binding.tfUsernameTitle to binding.tfUsernameTitle.transitionName,
                    binding.ifhUsername to binding.ifhUsername.transitionName
                )
            )
    }

    private fun goToRegistrationScreen() {
        exitTransition = transitionInflater().inflateTransition(
            android.R.transition.slide_left
        )
        findNavController()
            .navigate(
                LoginFragmentDirections
                    .actionLoginFragmentToFeatureRegistration(),
                FragmentNavigatorExtras(
                    binding.btnRegister to binding.btnRegister.transitionName
                )
            )
    }

    override fun onResume() {
        super.onResume()
        backgroundController.loadBackground(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_image_2
            )!!
        )
    }

    override fun reflectState(state: LoginViewState) {
        with(state) {
            goToRegistrationScreen?.getValue()?.also {
                goToRegistrationScreen()
            }
            goToRestorePasswordScreen?.getValue()?.also {
                goToRestorePasswordScreen()
            }
            showLoader(showLoader?.getValue() == true)
            showError?.getValue()?.also {
                showError(it)
            }
        }
    }

    private fun showError(it: Throwable) {
        Log.d("zulufta", "error: ${it.message}")
    }

    private fun showLoader(show: Boolean) {
        if (show) {
            Loader.show(childFragmentManager)
        } else {
            Loader.dismiss(childFragmentManager)
        }
    }

    override fun provideViewModel(): LoginViewModel {
        return viewModel
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        return FragmentLoginBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun provideModulesToLoad(): List<Module> {
        return listOf(LOGIN_MODULE)
    }

    override fun provideModulesToUnload(): List<Module> {
        return listOf(LOGIN_MODULE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTransactions()
    }

    private fun setupTransactions() {
        enterTransition = transitionInflater().inflateTransition(
            android.R.transition.slide_bottom
        ).apply {
            duration = ANIM_DURATION
        }
        returnTransition = transitionInflater()
            .inflateTransition(android.R.transition.slide_left)
        sharedElementReturnTransition = transitionInflater()
            .inflateTransition(
                R.transition.default_transition
            )
        allowReturnTransitionOverlap = false
        allowEnterTransitionOverlap = false
    }

    override fun onDestroy() {
        super.onDestroy()
        logoControllerHolder.detachController()
    }

    companion object {
        private const val ANIM_DURATION = 800L
    }
}