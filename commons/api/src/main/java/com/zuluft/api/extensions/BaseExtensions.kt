@file:Suppress("unused")

package com.zuluft.api.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

inline fun <T : Any> T?.notNull(callback: (T) -> Unit): T? {
    return this?.also(callback)
}

fun Context.colorOf(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun Context.drawableOf(@DrawableRes drawableRes: Int) : Drawable? {
    return ContextCompat.getDrawable(this, drawableRes)
}

fun Context.toast(@StringRes msg: Int, duration: Int = Toast.LENGTH_LONG) {
    toast(getString(msg), duration)
}

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, msg, duration).show()
}

fun Fragment.toast(@StringRes msg: Int, duration: Int = Toast.LENGTH_LONG) {
    toast(getString(msg), duration)
}

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), msg, duration).show()
}