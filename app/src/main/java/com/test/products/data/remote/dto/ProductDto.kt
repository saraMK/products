package com.test.products.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductDto( @field:SerializedName("id") val id:String,
                       @field:SerializedName("name")  val name:String,
                       @field:SerializedName("price")  val price:Int, )