package com.example.commerceapp.domain.enntity.product

import com.example.commerceapp.data.remote.model.product.Attribute
import com.example.commerceapp.data.remote.model.product.Brand

data class ProductEntity(
    val id: String?=null,
//    val attributes: List<Attribute>?=null,
    val averageReviewScore: Double?=null,
    val isBest: Boolean?=null,
//    val brand: Brand?=null,
//    val images: List<String>?=null,
    val isNewItem: Boolean?=null,
    val discountPrice: Int?=null,
    val discountRate: Int?=null,
    val name: String?=null,
    val productNo: String?=null,
    val salePrice: Int?=null,
)