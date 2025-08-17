package com.example.mviproductsapp.data.repository

import com.example.mviproductsapp.data.dto.ProductDto
import com.example.mviproductsapp.data.dto.ProductResponseDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getProducts(): Flow<ProductResponseDto>

    suspend fun getProductById(id: Int): Flow<ProductDto>
}