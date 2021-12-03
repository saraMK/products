package com.test.products.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class BaseViewModel(val dispatcherIo: CoroutineDispatcher):ViewModel() {
 }