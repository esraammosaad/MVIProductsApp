package com.example.mviproductsapp.data.data_sources.remote

import com.example.mviproductsapp.data.dto.ProductDto
import com.example.mviproductsapp.data.dto.ProductResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponseDto

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto
}