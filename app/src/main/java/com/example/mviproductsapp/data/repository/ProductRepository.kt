package com.example.mviproductsapp.data.repository

import com.example.mviproductsapp.data.data_sources.remote.RemoteDataSource
import com.example.mviproductsapp.data.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getProducts(): Flow<List<Product>> {
        return remoteDataSource.getProducts()
    }

    suspend fun getProductById(id: Int): Flow<Product> {
        return remoteDataSource.getProductById(id)
    }
}