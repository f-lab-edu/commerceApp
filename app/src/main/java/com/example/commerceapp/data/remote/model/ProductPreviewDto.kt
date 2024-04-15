package com.example.commerceapp.data.remote.model

import com.example.commerceapp.domain.model.product.ProductPreview
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductPreviewDto(
    val retailPrice: Int = 0,
    val basePrice: Int = 0,
    val discountedPrice: Int = 0,
    val discountRate: Double = 0.0,
    val expirationDate: String = "",
    val isSoldOut: Boolean = false,
    val mainImageUrl: String = "",
    val name: String,
    val no: String,
    val reviewCount: Int = 0,
    val adultVerificationFailed: Boolean = false,
    val isThirdPart: Boolean = false,
    val productVerticalSmallUrl: String = ""
) {
    fun mapToEntity(): ProductPreview {
        return ProductPreview(
            retailPrice,
            basePrice,
            discountedPrice,
            discountRate,
            expirationDate,
            isSoldOut,
            mainImageUrl,
            name,
            no,
            reviewCount,
            adultVerificationFailed
        )
    }
}