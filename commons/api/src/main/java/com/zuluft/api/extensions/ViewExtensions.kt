@file:Suppress("unused")

package com.zuluft.api.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Fragment.transitionInflater(): TransitionInflater {
    return TransitionInflater.from(requireContext())
}