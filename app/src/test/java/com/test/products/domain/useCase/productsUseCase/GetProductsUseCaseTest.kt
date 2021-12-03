package com.test.products.domain.useCase.productsUseCase

import com.test.products.data.remote.dto.ProductDto
import com.test.products.domain.mappers.MapProductDtoToProductModel.mapToProductModel
import com.test.products.domain.repo.DataRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class GetProductsUseCaseTest{

    private lateinit var repository: DataRepository
    private lateinit var useCase: GetProductsUseCase

    @Before
    fun setUp() {
        repository = mock(DataRepository::class.java)
        useCase = GetProductsUseCase(repository)
    }

    @Test
    fun `When_response_is_successful_Then_return_the_right_list_of_Products`() = runBlocking {
        //GIVEN
        val list = mutableListOf(ProductDto("1", "product1", 20))
        val mapperList =list.map { it.mapToProductModel() }
        `when`(repository.getProductsList()).thenReturn(
            list
        )

        //WHEN
        val emits = useCase.getProductsList().toList() // list of emits
        // loading
        assertEquals(emits.first().data, null)
        /// fetch list
        assertEquals(emits.last().data,mapperList)

    }


    @Test(expected = Exception::class)
    fun `When_response_is_not_successful_Then_throw_exception`() = runBlocking {
        //GIVEN
        `when`(useCase.getProductsList()).then { throw (Exception("no data")) }

        //WHEN
        val emits = useCase.getProductsList().toList()

        //THEN
        assertEquals(emits.first().data, null)
        assertEquals(emits.last().data, null)
        assertEquals(emits.last().message, notNull())
        assertEquals(emits.last().message, "no data")
    }

}