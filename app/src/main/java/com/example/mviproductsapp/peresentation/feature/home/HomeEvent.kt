package com.example.mviproductsapp.peresentation.feature.home

import com.example.productsapp.utils.NavigationRoutes

sealed class HomeEvent {
    data class NavigateToDetails(val productId : Int) : HomeEvent()
    data class ShowToast(val message : String) : HomeEvent()
}