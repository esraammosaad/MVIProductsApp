package com.example.mviproductsapp.home

sealed class HomeIntent {
    data object GetProducts : HomeIntent()
    data class SelectProduct(val id: Int) : HomeIntent()
}