package com.example.commerceapp.domain.model.product

data class Product(
    val retailPrice: Int,
    val basePrice: Int,
    val discountedPrice: Int,
    val discountRate: Double,
    val expirationDate: String,
    val isSoldOut: Boolean,
    val mainImageUrl: String,
    val name: String,
    val no: String,
    val reviewCount: Int,
    val shortDescription: String,
    val sellerName: String,
    val adultVerificationFailed: Boolean,
    val isThirdPart: Boolean,
    val productVerticalSmallUrl: String,
    val masterProductCode: String,
    val masterProductName: String,
    val dealProducts: List<Long>
)