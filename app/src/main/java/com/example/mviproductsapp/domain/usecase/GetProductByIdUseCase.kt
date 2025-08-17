package com.example.mviproductsapp.domain.usecase

import com.example.mviproductsapp.domain.model.Product
import com.example.mviproductsapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductByIdUseCase(private val productRepository: ProductRepository) {
    suspend fun getProductById(id: Int): Flow<Product> {
        return productRepository.getProductById(id)
    }
}