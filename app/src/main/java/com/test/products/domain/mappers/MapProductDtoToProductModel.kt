package com.test.products.domain.mappers

import com.test.products.data.remote.dto.ProductDto
import com.test.products.domain.models.ProductModel

object MapProductDtoToProductModel {
    fun ProductDto.mapToProductModel(): ProductModel = ProductModel(
        productId = id,
        productName = name,
        productPrice = price
    )
}
