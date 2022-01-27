@file:Suppress("unused")

package com.zuluft.koin_api.controllers.fragments

import android.os.Bundle
import com.zuluft.api.controllers.fragments.BaseDialogFragment
import com.zuluft.api.viewModels.BaseViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

abstract class BaseFeatureDialogFragment<VIEW_STATE, VIEW_MODEL : BaseViewModel<VIEW_STATE>> :
    BaseDialogFragment<VIEW_STATE, VIEW_MODEL>(),
    com.zuluft.koin_api.api.ModulesLoader {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModules()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadModules()
    }

    open fun loadModules() {
        loadKoinModules(provideModulesToLoad())
    }

    open fun unloadModules() {
        unloadKoinModules(provideModulesToUnload())
    }
}