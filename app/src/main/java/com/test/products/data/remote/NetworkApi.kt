package com.test.products.data.remote

import com.test.products.common.Resource
import com.test.products.data.remote.dto.ProductDto
import retrofit2.http.GET

interface NetworkApi {

    @GET("interview/products")
    suspend fun getProductsList(
    ): List<ProductDto>
}