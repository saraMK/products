package com.test.products.domain.repo

import com.test.products.data.remote.dto.ProductDto

interface DataRepository {
    suspend fun getProductsList():List<ProductDto>
}