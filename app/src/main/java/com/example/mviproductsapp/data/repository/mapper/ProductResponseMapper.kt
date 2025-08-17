package com.example.mviproductsapp.data.repository.mapper

import com.example.mviproductsapp.data.dto.ProductResponseDto
import com.example.mviproductsapp.domain.model.ProductResponse

fun ProductResponseDto.toDomain(): ProductResponse {
    return ProductResponse(
        products = products?.map { it.toDomain() } ?: emptyList()
    )
}