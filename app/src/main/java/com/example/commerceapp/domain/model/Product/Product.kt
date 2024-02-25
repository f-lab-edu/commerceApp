package com.example.commerceapp.domain.model.Product

data class Product(
    val id: String? = null,
    val averageReviewScore: Double? = null,
    val brand: Brand? = null,
    val images: List<String>? = null,
    val isBest: Boolean? = null,
    val isNewItem: Boolean? = null,
    val discountPrice: Int? = null,
    val discountRate: Int? = null,
    val name: String? = null,
    val productNo: String? = null,
    val salePrice: Int? = null,
)