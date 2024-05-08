package com.example.commerceapp.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDto(
    val retailPrice: Int? = 0,
    val basePrice: Int? = 0,
    val discountedPrice: Int? = 0,
    val discountRate: Double? = 0.0,
    val expirationDate: String? = "",
    val isSoldOut: Boolean? = false,
    val mainImageUrl: String? = "",
    val name: String? = "",
    val no: Long? = 0,
    val reviewCount: Int? = 0,
    val shortDescription: String? = "",
    val sellerName: String? = "",
    val adultVerificationFailed: Boolean? = false,
    val isThirdPart: Boolean? = false,
    val productVerticalSmallUrl: String? = "",
    val masterProductCode: String? = "",
    val masterProductName: String? = "",
    val dealProducts: List<Long>? = emptyList()
)