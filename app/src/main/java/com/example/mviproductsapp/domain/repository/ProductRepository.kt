package com.example.mviproductsapp.domain.repository

import com.example.mviproductsapp.domain.model.Product
import com.example.mviproductsapp.domain.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Flow<ProductResponse>
    suspend fun getProductById(id: Int): Flow<Product>
}