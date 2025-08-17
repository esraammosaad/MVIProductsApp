package com.example.mviproductsapp.data.data_sources.remote

import com.example.mviproductsapp.data.dto.ProductDto
import com.example.mviproductsapp.data.dto.ProductResponseDto
import com.example.mviproductsapp.data.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun getProducts(): Flow<ProductResponseDto> {
        return flowOf(apiService.getProducts())
    }

    override suspend fun getProductById(id: Int): Flow<ProductDto> {
        return flowOf(apiService.getProductById(id))
    }
}