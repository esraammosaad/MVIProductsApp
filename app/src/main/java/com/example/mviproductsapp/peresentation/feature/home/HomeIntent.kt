package com.example.mviproductsapp.peresentation.feature.home

sealed class HomeIntent {
    data object GetProducts : HomeIntent()
}