package com.zuluft.compose_navigation.api.manager

import androidx.lifecycle.LiveData
import com.zuluft.compose_navigation.api.NavigationCommand
import com.zuluft.compose_navigation.api.NavigationEntry

interface NavigationManager {
    fun navigationCommandObserver(): LiveData<NavigationCommand>
    fun entriesObserver(): LiveData<List<NavigationEntry<*>>>
    fun navigate(command: NavigationCommand)
    fun addEntries(entries: List<NavigationEntry<*>>)
}