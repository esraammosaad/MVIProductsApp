package com.example.mviproductsapp.details

sealed class DetailsIntent {
    data object GetProduct : DetailsIntent()
}