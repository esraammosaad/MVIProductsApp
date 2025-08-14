package com.example.productsapp.utils

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoutes {
    @Serializable
    data object HomeScreen : NavigationRoutes()
    @Serializable
    data class DetailsScreen(val productId: Int) : NavigationRoutes()
}