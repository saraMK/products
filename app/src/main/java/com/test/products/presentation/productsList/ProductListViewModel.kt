package com.test.products.presentation.productsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.products.ConnectivityManager
import com.test.products.common.Resource
import com.test.products.common.ActivityState
import com.test.products.domain.models.ProductModel
import com.test.products.domain.useCase.productsUseCase.GetProductsUseCase
import com.test.products.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    val connectivityManager: ConnectivityManager,
    val useCase: GetProductsUseCase,
    dispatcherIo: CoroutineDispatcher
) : BaseViewModel(dispatcherIo) {
    private var _state = MutableLiveData<ActivityState<List<ProductModel>?>>(ActivityState.NothingState)
    val state: LiveData<ActivityState<List<ProductModel>?>>
        get() = _state

    init {
       // getProducts()
    }
    fun getProducts() {

        if (connectivityManager.isConnected()) {
            viewModelScope.launch(dispatcherIo) {
                useCase.getProductsList().collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.postValue(ActivityState.Success(data = result.data))
                         }
                        is Resource.Error -> {
                            _state.postValue(ActivityState.Error(result.message ?: ""))
                         }
                        is Resource.Loading -> {
                            _state.postValue(ActivityState.Loading)
                         }
                    }

                }


            }
        }else {
            _state.postValue(ActivityState.Error("Network teardown"))
        }
    }


}