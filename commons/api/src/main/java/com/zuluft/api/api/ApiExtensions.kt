@file:Suppress("unused")

package com.zuluft.api.api

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

private fun closeKeyboard(context: Context) {
    val activity = context as Activity
    val view = activity.currentFocus
    if (view != null) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

private fun changeKeyboardAppearance(context: Context, resize: Boolean) {
    (context as Activity).window
        .setSoftInputMode(
            when (resize) {
                false -> WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                else -> WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            }
        )
}

fun Fragment.closeKeyboard() {
    closeKeyboard(requireContext())
}

fun Fragment.changeKeyboardAppearance(resize: Boolean) {
    changeKeyboardAppearance(requireContext(), resize)
}

fun Activity.closeKeyboard() {
    closeKeyboard(this)
}

fun Activity.changeKeyboardAppearance(resize: Boolean) {
    changeKeyboardAppearance(this, resize)
}

