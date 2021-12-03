package com.test.products.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(
    val productId:String,
    val productName:String,
    val productPrice:Int
): Parcelable