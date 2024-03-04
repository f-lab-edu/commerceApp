package com.example.commerceapp.data.remote.model

import com.example.commerceapp.domain.model.Attribute

data class ProductDetail(
    val id: String? = null,
    val attributes: List<Attribute>? = emptyList(),정
    val averageReviewScore: Double = 0.0,
    val isBest: Boolean = false,
    val brand: Brand? = null,
    val contentText: String = "",
    val images: List<Image>? = emptyList(),
    val isNewItem: Boolean = false,
    val menus: List<String>? = emptyList(),
    val discountPrice: Int = 0,
    val discountRate: Int = 0,
    val modelNumber: String? = null,
    val name: String? = null,
    val category: Category? = null,
    val nvMid: Long? = null,
    val productNo: String? = null,
    val salePrice: Int = 0,
    val soldOut: Boolean = false,
    val standardColors: List<Any>? = emptyList(),
    val standardSizes: List<Any>? = emptyList(),
    val tags: List<Tag> = emptyList(),
    val totalReviewCount: Int = 0,
    val totalSaleCount: Int = 0,
    val unionCategories: List<Any> = emptyList(),
)