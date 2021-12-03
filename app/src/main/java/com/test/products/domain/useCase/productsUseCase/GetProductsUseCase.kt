package com.test.products.domain.useCase.productsUseCase

import android.util.Log
import com.test.products.common.Resource
import com.test.products.domain.mappers.MapProductDtoToProductModel.mapToProductModel
 import com.test.products.domain.models.ProductModel
import com.test.products.domain.repo.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(val repo: DataRepository) {

     suspend fun getProductsList(): Flow<Resource<List<ProductModel>>> = flow {
            try {
                emit(Resource.Loading())
                val result = repo.getProductsList()
                val mapper = result.map { it.mapToProductModel() }
              emit(Resource.Success(mapper))
            } catch (e: Exception) {
                emit(Resource.Error<List<ProductModel>>(e.message?:"",null))
            }
       }

    }