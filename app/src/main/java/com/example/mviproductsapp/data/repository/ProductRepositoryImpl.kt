package com.example.mviproductsapp.data.repository

import com.example.mviproductsapp.data.data_sources.remote.RemoteDataSourceImpl
import com.example.mviproductsapp.data.repository.mapper.toDomain
import com.example.mviproductsapp.domain.model.Product
import com.example.mviproductsapp.domain.model.ProductResponse
import com.example.mviproductsapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(private val remoteDataSource: RemoteDataSourceImpl) :
    ProductRepository {
    override suspend fun getProducts(): Flow<ProductResponse> {
        return remoteDataSource.getProducts().map { it.toDomain() }
    }

    override suspend fun getProductById(id: Int): Flow<Product> {
        return remoteDataSource.getProductById(id).map { it.toDomain() }
    }
}