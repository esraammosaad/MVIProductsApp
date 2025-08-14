package com.example.mviproductsapp.data.data_sources.remote

import com.example.mviproductsapp.data.model.Product
import com.example.mviproductsapp.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

}