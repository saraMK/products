package com.test.products.presentation.productsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.products.databinding.ProductItemBinding
import com.test.products.domain.models.ProductModel
import javax.inject.Inject


class ProductsAdapter @Inject constructor() : RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {
    var action:ProductsAction?=null
    val list:MutableList<ProductModel> = mutableListOf()
    class MyViewHolder constructor(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false
            ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model =list.get(position)
        holder.binding.productName.text= model.productName
        holder.binding.productPrice.text= "${model.productPrice}"
        holder.itemView.setOnClickListener { action?.onProductClick(model) }
    }

    override fun getItemCount(): Int =list.size

    interface ProductsAction{
        fun onProductClick(model:ProductModel)
    }
}