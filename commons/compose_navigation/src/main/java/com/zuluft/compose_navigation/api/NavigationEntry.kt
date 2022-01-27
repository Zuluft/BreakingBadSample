package com.zuluft.compose_navigation.api

import androidx.compose.runtime.Composable

abstract class NavigationEntry<T : Any>(
    private val id: String,
    private val argument: T? = null
) {

    fun generateDestination(): String {
        return "$id${
            if (argument != null) {
                "/{arg}"
            } else {
                ""
            }
        }"
    }

    abstract fun content(): @Composable () -> Unit

}