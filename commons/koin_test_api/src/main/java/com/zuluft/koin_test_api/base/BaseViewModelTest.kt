package com.zuluft.koin_test_api.base

import com.zuluft.api.viewModels.BaseViewModel
import org.junit.Before


abstract class BaseViewModelTest<VIEW_STATE, VIEW_MODEL : BaseViewModel<VIEW_STATE>> : BaseTest() {

    private lateinit var viewModel: VIEW_MODEL

    abstract fun provideViewModel(): VIEW_MODEL

    protected fun getViewModel(): VIEW_MODEL {
        return viewModel
    }

    protected fun getViewState(): VIEW_STATE? {
        return getViewModel().getLastViewState()
    }

    @Before
    fun initViewModel() {
        viewModel = provideViewModel()
    }
}