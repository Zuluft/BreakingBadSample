package com.zuluft.compose_navigation.api.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zuluft.compose_navigation.api.NavigationCommand
import com.zuluft.compose_navigation.api.NavigationEntry

class NavigationManagerImpl : NavigationManager {

    private val commandsObserver = MutableLiveData<NavigationCommand>()
    private val entriesObserver = MutableLiveData<List<NavigationEntry<*>>>()

    override fun navigationCommandObserver(): LiveData<NavigationCommand> {
        return commandsObserver
    }

    override fun entriesObserver(): LiveData<List<NavigationEntry<*>>> {
        return entriesObserver
    }

    override fun navigate(command: NavigationCommand) {
        commandsObserver.postValue(command)
    }

    override fun addEntries(entries: List<NavigationEntry<*>>) {
        entriesObserver.postValue(
            mutableListOf<NavigationEntry<*>>().apply {
                val oldValue = entriesObserver.value ?: emptyList()
                addAll(oldValue)
                addAll(entries)
            })
    }
}