package com.example.mviproductsapp.details

import com.example.mviproductsapp.data.model.Product

sealed class DetailsState {
    data object Idle : DetailsState()
    data object Loading : DetailsState()
    data class Success(val product: Product) : DetailsState()
    data class Failure(val message: String) : DetailsState()
}