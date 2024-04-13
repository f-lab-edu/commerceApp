package com.example.commerceapp.domain.model.product

import com.example.commerceapp.domain.model.Brand

data class ProductPreview(
    val id: String? = null,
    val averageReviewScore: Double = 0.0,
    val brand: Brand? = null,
    val images: List<String> = emptyList(),
    val isBest: Boolean = false,
    val isNewItem: Boolean = false,
    val discountPrice: Int = 0,
    val discountRate: Int = 0,
    val name: String = "",
    val productNo: String? = null,
    val salePrice: Int = 0,
    val bookMark:Boolean = false
)