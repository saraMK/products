package com.test.products.domain.mappers

import com.test.products.data.remote.dto.ProductDto
import com.test.products.domain.mappers.MapProductDtoToProductModel.mapToProductModel
import com.test.products.domain.models.ProductModel
import org.junit.Assert.*
import org.junit.Test

class MapProductDtoToProductModelTest{

    @Test
    fun `map productDto to ProductModel success`(){
        val productDto =ProductDto("1","name",30)

        val productModel =productDto.mapToProductModel()

        assertEquals(productModel,ProductModel("1","name",30))
    }
}