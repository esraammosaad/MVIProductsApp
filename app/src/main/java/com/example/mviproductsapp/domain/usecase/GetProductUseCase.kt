package com.example.mviproductsapp.domain.usecase

import com.example.mviproductsapp.domain.model.ProductResponse
import com.example.mviproductsapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductUseCase(private val productRepository: ProductRepository) {
    suspend fun getProducts(): Flow<ProductResponse> {
        return productRepository.getProducts()
    }
}