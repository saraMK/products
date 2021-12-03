package com.test.products.data.repo

import com.test.products.data.remote.NetworkApi
import com.test.products.data.remote.dto.ProductDto
import com.test.products.domain.repo.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(val api: NetworkApi): DataRepository
{
    override suspend fun getProductsList(): List<ProductDto> {
        return api.getProductsList()
    }
}