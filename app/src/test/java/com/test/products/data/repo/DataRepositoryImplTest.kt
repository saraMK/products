package com.test.products.data.repo

import com.test.products.data.remote.NetworkApi
import com.test.products.data.remote.dto.ProductDto
import com.test.products.domain.mappers.MapProductDtoToProductModel.mapToProductModel
import com.test.products.domain.repo.DataRepository
import com.test.products.domain.useCase.productsUseCase.GetProductsUseCase
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class DataRepositoryImplTest{

    private lateinit var repository: DataRepository
    private lateinit var api: NetworkApi

    @Before
    fun setUp() {
        api = Mockito.mock(NetworkApi::class.java)
        repository = DataRepositoryImpl(api)
    }


    @Test
    fun `When_response_is_successful_Then_return_the_right_list_of_Products`() = runBlocking {
        //GIVEN
        val list = mutableListOf(ProductDto("1", "product1", 20))
         Mockito.`when`(api.getProductsList()).thenReturn(
            list
        )
        //WHEN
        val products = repository.getProductsList()
        assertEquals(products, list)
    }


    @Test(expected = Exception::class)
    fun `When_response_is_not_successful_Then_throw_exception`() = runBlocking {
        //GIVEN
        Mockito.`when`(api.getProductsList()).then { throw (Exception()) }

        //WHEN
        val error = repository.getProductsList()

        //THEN
        assertEquals(error, Exception())
    }
}