package com.zuluft.koin_api.controllers.activities

import android.os.Bundle
import com.zuluft.koin_api.api.ModulesLoader
import com.zuluft.api.controllers.activities.BaseActivity
import com.zuluft.api.viewModels.BaseViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

abstract class BaseFeatureActivity<VIEW_STATE, VIEW_MODEL : BaseViewModel<VIEW_STATE>> :
    BaseActivity<VIEW_STATE, VIEW_MODEL>(),
    ModulesLoader {

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