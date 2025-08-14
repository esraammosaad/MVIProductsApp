package com.example.mviproductsapp.peresentation.feature.details
sealed class DetailsIntent {
    data object GetProduct : DetailsIntent()
}