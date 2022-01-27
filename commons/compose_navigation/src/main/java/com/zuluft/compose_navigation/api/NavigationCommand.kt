package com.zuluft.compose_navigation.api

import androidx.navigation.NamedNavArgument

data class NavigationCommand(
    val arguments: List<NamedNavArgument>,
    val destination: String
)