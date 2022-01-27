package com.zuluft.api.controllers.activities

import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zuluft.api.api.internal.DisposablesHolder
import com.zuluft.api.api.internal.UiLayer
import com.zuluft.api.api.internal.ViewModelProvider
import com.zuluft.api.viewModels.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseActivity<VIEW_STATE, VIEW_MODEL : BaseViewModel<VIEW_STATE>> :
    ViewModelProvider<VIEW_STATE, VIEW_MODEL>,
    UiLayer<VIEW_STATE>,
    AppCompatActivity(),
    DisposablesHolder {

    private lateinit var viewModel: VIEW_MODEL

    private var compositeDisposable: CompositeDisposable? = null

    private var lastViewState: VIEW_STATE? = null

    private val dialogsList: ArrayList<Dialog> = ArrayList()


    protected fun getLastViewState(): VIEW_STATE? {
        return lastViewState
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null && baseContext != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()
        prepareView()
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        viewModel = provideViewModel()
        viewModel.getLiveViewState()
            .observe(this,
                {
                    reflectState(it)
                    lastViewState = it
                })
    }

    fun getViewModel(): VIEW_MODEL? {
        return if (this::viewModel.isInitialized) {
            viewModel
        } else {
            null
        }
    }

    protected fun registerDialog(dialog: Dialog) {
        dialogsList.add(dialog)
    }

    protected fun dismissAndClearDialogs() {
        for (dialog in dialogsList) {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
        dialogsList.clear()
    }

    override fun registerDisposables(vararg disposables: Disposable) {
        compositeDisposable!!.addAll(*disposables)
    }

    override fun clearDisposables() {
        if (compositeDisposable != null) {
            compositeDisposable!!.dispose()
            compositeDisposable!!.clear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearDisposables()
    }

}