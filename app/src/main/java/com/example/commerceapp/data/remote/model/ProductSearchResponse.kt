package com.example.commerceapp.data.remote.model

import com.example.commerceapp.domain.model.product.ProductPreview


data class ProductSearchResponse(
    val startPage:String,
    val data:List<ProductDto>
){
    fun mapToResultEntity():List<ProductPreview>{
        return data.map { it.mapToProduct() }
    }
}