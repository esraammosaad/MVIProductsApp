package com.example.mviproductsapp.data.data_sources.remote

import com.example.mviproductsapp.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getProducts(): Flow<List<Product>> {
        return flowOf(apiService.getProducts().products)
    }

    suspend fun getProductById(id: Int): Flow<Product> {
        return flowOf(apiService.getProductById(id))
    }
}