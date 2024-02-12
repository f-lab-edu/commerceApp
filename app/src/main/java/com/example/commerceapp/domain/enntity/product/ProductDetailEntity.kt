package com.example.commerceapp.domain.enntity.product

import com.example.commerceapp.data.remote.model.product.Brand

data class ProductDetailEntity(
    val id: String? = null,
    val averageReviewScore: Double? = null,
    val isBest: Boolean? = null,
//    val brand: Brand? = null,
    val contentText: String? = null,
    val images: List<String>? = null,
    val isNewItem: Boolean? = null,
    val menus: List<String>? = null,
    val discountPrice: Int? = null,
    val discountRate: Int? = null,
    val modelNumber: String? = null,
    val name: String? = null,
    val npay: Boolean? = null,
    val nvMid: Long? = null,
    val productNo: String? = null,
    val salePrice: Int? = null,
    val soldout: Boolean? = null,
    val standardColors: List<Any>? = null,
    val standardSizes: List<Any>? = null,
    val totalReviewCount: Int? = null,
    val totalSaleCount: Int? = null,
    val unionCategories: List<Any>? = null
//     val attributes: List<Attribute>,
//     val naverShoppingCategory: NaverShoppingCategory,
//     val tags: List<Tag>,
)