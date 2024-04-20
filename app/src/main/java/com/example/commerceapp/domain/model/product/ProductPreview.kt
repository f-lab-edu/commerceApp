package com.example.commerceapp.domain.model.product

data class ProductPreview(
    val retailPrice: Int,
    val basePrice: Int,
    val discountedPrice: Int,
    val discountRate: Double,
    val expirationDate: String?,
    val isSoldOut: Boolean,
    val mainImageUrl: String,
    val name: String,
    val no: String,
    val reviewCount: Int,
    val adultVerificationFailed: Boolean,
)