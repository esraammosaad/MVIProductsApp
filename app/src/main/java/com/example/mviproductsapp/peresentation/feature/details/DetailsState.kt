package com.example.mviproductsapp.peresentation.feature.details

import com.example.mviproductsapp.domain.model.Product

sealed class DetailsState {
    data object Idle : DetailsState()
    data object Loading : DetailsState()
    data class Success(val product: Product) : DetailsState()
    data class Failure(val message: String) : DetailsState()
}