package com.test.products.presentation.productsList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.testcoroutinesrule.TestCoroutineRule
import com.test.products.ConnectivityManager
import com.test.products.MainCoroutineRule
import com.test.products.common.Resource
import com.test.products.common.ActivityState
import com.test.products.domain.models.ProductModel
import com.test.products.domain.useCase.productsUseCase.GetProductsUseCase
import com.test.products.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito


@ExperimentalCoroutinesApi
class ProductListViewModelTest {


    private lateinit var connection: ConnectivityManager
    private lateinit var useCase: GetProductsUseCase
    private lateinit var viewModel: ProductListViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        useCase = Mockito.mock(GetProductsUseCase::class.java)
        connection = Mockito.mock(ConnectivityManager::class.java)
        viewModel = ProductListViewModel(connection, useCase, testDispatcher)
    }

    @Test
    fun `When open screen without call any methods`() {
        val value = viewModel.state.getOrAwaitValue()
        assert(value is ActivityState.NothingState)
    }

    @Test
    fun `When network connection is teardown`() = runBlocking {
        Mockito.`when`(connection.isConnected()).thenReturn(false)
        viewModel.getProducts()
        val value = viewModel.state.getOrAwaitValue()
        assert(value is ActivityState.Error)
    }

    @Test
    fun `When_Loading_list_of_Products`() = runBlocking {
        //GIVEN
        Mockito.`when`(connection.isConnected()).thenReturn(true)
        Mockito.`when`(useCase.getProductsList()).thenReturn(
            flow<Resource<List<ProductModel>>> {
                emit(Resource.Loading())
            }
        )

        //WHEN
        viewModel.getProducts()
        val value = viewModel.state.getOrAwaitValue()
        assertEquals(ActivityState.Loading, value)
        assert(value is ActivityState.Loading)
    }

    @Test
    fun `When_response_is_successful_Then_return_the_right_list_of_Products`() =
        runBlocking {
            Mockito.`when`(connection.isConnected()).thenReturn(
                true
            )
            //GIVEN
            val list = mutableListOf(ProductModel("1", "product1", 20))
            Mockito.`when`(useCase.getProductsList()).thenReturn(
                flow<Resource<List<ProductModel>>> {
                    emit(Resource.Success(list))
                }
            )
            //WHEN
            viewModel.getProducts()
            val value = viewModel.state.getOrAwaitValue()
            //Then
            assert(value is ActivityState.Success<List<ProductModel>?>)
            //WHEN
            val products = value as ActivityState.Success<List<ProductModel>>
            //THEN
            assertEquals(list, products.data)
        }


    @Test
    fun `When_response_is_not_successful_with_an_error`() =
        runBlocking {
            Mockito.`when`(connection.isConnected()).thenReturn(
                true
            )
            //GIVEN
            Mockito.`when`(useCase.getProductsList()).thenReturn(
                flow<Resource<List<ProductModel>>> {
                    emit(Resource.Error("no Data"))
                }
            )

            //WHEN
            viewModel.getProducts()
            val value = viewModel.state.getOrAwaitValue()
            //THEN
            assert(value is ActivityState.Error)
            //WHEN
            value as ActivityState.Error

            //THEN
            assertEquals("no Data", value.message)
        }
}