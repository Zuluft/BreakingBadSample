@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.zuluft.api.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zuluft.api.api.internal.DisposablesHolder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<VIEW_STATE> :
  ViewModel(),
  DisposablesHolder {

  protected val compositeDisposable = CompositeDisposable()

  private val liveViewState = MutableLiveData<VIEW_STATE>()

  override fun registerDisposables(vararg disposables: Disposable) {
    compositeDisposable.addAll(*disposables)
  }

  init {
    pushInitialState()
  }

  private fun pushInitialState() {
    setState(getInitialState())
  }

  override fun onCleared() {
    super.onCleared()
    clearDisposables()
  }

  protected fun setState(constructedState: VIEW_STATE) {
    liveViewState.postValue(constructedState)
  }

  protected fun constructState(newStateFactory: VIEW_STATE.() -> VIEW_STATE): VIEW_STATE {
    return (liveViewState.value ?: getInitialState()).newStateFactory()
  }

  fun getLastViewState(): VIEW_STATE? {
    return liveViewState.value
  }

  override fun clearDisposables() {
    compositeDisposable.dispose()
    compositeDisposable.clear()
  }

  protected abstract fun getInitialState(): VIEW_STATE

  fun getLiveViewState(): LiveData<VIEW_STATE> = liveViewState
}