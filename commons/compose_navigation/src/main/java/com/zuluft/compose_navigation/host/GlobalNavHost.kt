package com.zuluft.compose_navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zuluft.compose_navigation.api.NavigationEntry

@Composable
fun GlobalNavHost(startEntry: NavigationEntry<*>, entries: List<NavigationEntry<*>>) {
    val navController = rememberNavController()
    val currentEntries = remember { mutableStateOf(entries) }
    NavHost(
        navController = navController,
        startDestination = startEntry.generateDestination()
    ) {
        composable(startEntry.generateDestination()) {
            startEntry.content()()
        }
        currentEntries.value.forEach { navEntry ->
            composable(navEntry.generateDestination()) {
                navEntry.content()()
            }
        }
    }
}