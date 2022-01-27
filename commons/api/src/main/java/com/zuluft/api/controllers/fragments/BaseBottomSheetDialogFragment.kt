@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.zuluft.api.controllers.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zuluft.api.api.closeKeyboard
import com.zuluft.api.api.internal.DisposablesHolder
import com.zuluft.api.api.internal.UiLayer
import com.zuluft.api.api.internal.ViewModelProvider
import com.zuluft.api.viewModels.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseBottomSheetDialogFragment<VIEW_STATE, VIEW_MODEL : BaseViewModel<VIEW_STATE>> :
    ViewModelProvider<VIEW_STATE, VIEW_MODEL>,
    UiLayer<VIEW_STATE>,
    BottomSheetDialogFragment(),
    DisposablesHolder {

    private var compositeDisposable: CompositeDisposable? = null

    private val dialogsList: ArrayList<Dialog> = ArrayList()

    private lateinit var viewModel: VIEW_MODEL

    private var lastViewState: VIEW_STATE? = null

    protected fun getLastViewState(): VIEW_STATE? {
        return lastViewState
    }

    @SuppressLint("ClickableViewAccessibility")
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
            setStyle(DialogFragment.STYLE_NO_FRAME, DialogFragment.STYLE_NO_FRAME)
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
        prepareView()
        subscribeViewModel()
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


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet =
                    findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.also { frame ->
                    val bottomSheetBehavior = BottomSheetBehavior.from(frame)
                    bottomSheetBehavior.skipCollapsed = true
                    bottomSheetBehavior.peekHeight = frame.height
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
    }

    override fun registerDisposables(vararg disposables: Disposable) {
        compositeDisposable!!.addAll(*disposables)
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?): View?

    override fun onDestroyView() {
        dismissAndClearDialogs()
        if (compositeDisposable != null) {
            compositeDisposable!!.dispose()
            compositeDisposable!!.clear()
        }
        super.onDestroyView()
        closeKeyboard()
    }
}