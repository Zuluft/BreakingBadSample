package com.zuluft.base

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children

abstract class BaseComponent : ConstraintLayout {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr

    ) {
        isTransitionGroup = true
    }


    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>?) {
        dispatchFreezeSelfOnly(container)
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>?) {
        dispatchThawSelfOnly(container)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return Bundle().apply {
            putParcelable(KEY_SUPER_STATE, super.onSaveInstanceState())
            putSparseParcelableArray(KEY_SPARSE_STATE, saveChildViewStates())
        }
    }

    private fun saveChildViewStates(): SparseArray<Parcelable> {
        val childViewStates = SparseArray<Parcelable>()
        children.forEach {
            it.saveHierarchyState(childViewStates)
        }
        return childViewStates
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var newState = state
        if (newState is Bundle) {
            val childrenState = newState.getSparseParcelableArray<Parcelable>(KEY_SPARSE_STATE)
            childrenState?.let { restoreChildViewStates(it) }
            newState = newState.getParcelable(KEY_SUPER_STATE)
        }
        super.onRestoreInstanceState(newState)
    }

    private fun restoreChildViewStates(childViewStates: SparseArray<Parcelable>) {
        children.forEach {
            it.restoreHierarchyState(childViewStates)
        }
    }

    companion object {
        private const val KEY_SPARSE_STATE = "KEY_SPARSE_STATE"
        private const val KEY_SUPER_STATE = "KEY_SUPER_STATE"
    }
}