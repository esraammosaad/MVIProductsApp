package com.example.mviproductsapp.data.repository.mapper

import com.example.mviproductsapp.data.dto.ProductDto
import com.example.mviproductsapp.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id ?: 0,
        title = title ?: "",
        description = description ?: "",
        category = category ?: "",
        price = price ?: 0.0,
        rating = rating ?: 0.0,
        stock = stock ?: 0,
        brand = brand ?: "",
        thumbnail = thumbnail ?: "",
    )
}