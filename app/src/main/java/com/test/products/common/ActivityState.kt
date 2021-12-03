package com.test.products.common



sealed class ActivityState<out T> {
    data class Success<T>(val data: T) : ActivityState<T>()
    data class Error(val message: String?="") : ActivityState<Nothing>()
    object Loading : ActivityState<Nothing>()
    object NothingState : ActivityState<Nothing>()
}