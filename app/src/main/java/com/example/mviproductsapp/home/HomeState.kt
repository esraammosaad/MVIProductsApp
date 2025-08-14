package com.example.mviproductsapp.home

import com.example.mviproductsapp.data.model.Product

sealed class HomeState {
    data object Idle : HomeState()
    data object Loading : HomeState()
    data class Success(val products: List<Product>) : HomeState()
    data class Failure(val message: String) : HomeState()
}