@file:Suppress("MemberVisibilityCanBePrivate")

package com.zuluft.api.controllers.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.zuluft.api.api.internal.DisposablesHolder
import com.zuluft.api.api.internal.UiLayer
import com.zuluft.api.api.internal.ViewModelProvider
import com.zuluft.api.viewModels.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDialogFragment<VIEW_STATE, VIEW_MODEL : BaseViewModel<VIEW_STATE>> :
    ViewModelProvider<VIEW_STATE, VIEW_MODEL>,
    UiLayer<VIEW_STATE>,
    DialogFragment(),
    DisposablesHolder {


    private var compositeDisposable: CompositeDisposable? = null

    private val dialogsList: ArrayList<Dialog> = ArrayList()

    private lateinit var viewModel: VIEW_MODEL

    private var lastViewState: VIEW_STATE? = null

    protected fun getLastViewState(): VIEW_STATE? {
        return lastViewState
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        compositeDisposable = CompositeDisposable()

        val window = dialog?.window
        if (window != null) {
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            setStyle(STYLE_NO_FRAME, android.R.style.Theme)
        }

        return createView(inflater, container)
    }

    protected fun registerDialog(dialog: Dialog) {
        dialogsList.add(dialog)
    }

    protected fun isDialogShowing(): Boolean {
        for (dialog in dialogsList) {
            if (dialog.isShowing) {
                return true
            }
        }
        return false
    }

    protected fun dismissAndClearDialogs() {
        for (dialog in dialogsList) {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
        dialogsList.clear()
    }

    fun getViewModel(): VIEW_MODEL? {
        return if (this::viewModel.isInitialized) {
            viewModel
        } else {
            null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeViewModel()
        prepareView()
    }

    private fun subscribeViewModel() {
        viewModel = provideViewModel()
        viewModel.getLiveViewState()
            .observe(viewLifecycleOwner,
                {
                    reflectState(it)
                    lastViewState = it
                })
    }

    override fun registerDisposables(vararg disposables: Disposable) {
        compositeDisposable!!.addAll(*disposables)
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?): View?

    override fun clearDisposables() {
        if (compositeDisposable != null) {
            compositeDisposable!!.dispose()
            compositeDisposable!!.clear()
        }
    }

    override fun onDestroyView() {
        dismissAndClearDialogs()
        clearDisposables()
        super.onDestroyView()
    }

}
