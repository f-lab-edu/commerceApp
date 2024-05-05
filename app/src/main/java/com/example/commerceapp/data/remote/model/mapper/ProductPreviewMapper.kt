package com.example.commerceapp.data.remote.model.mapper

import com.example.commerceapp.data.remote.model.ProductPreviewDto
import com.example.commerceapp.domain.model.product.ProductPreview

class ProductPreviewMapper {
    fun mapToEntity(dto: ProductPreviewDto): ProductPreview {
        return ProductPreview(
            dto.retailPrice ?: 0,
            dto.basePrice ?: 0,
            dto.discountedPrice ?: 0,
            dto.discountRate ?: 0.0,
            dto.expirationDate,
            dto.isSoldOut ?: false,
            dto.mainImageUrl ?: "",
            dto.name ?: "",
            dto.no.toString(),
            dto.reviewCount ?: 0,
            dto.adultVerificationFailed ?: false
        )
    }

    fun mapToDto(entity: ProductPreview): ProductPreviewDto {
        return ProductPreviewDto(
            entity.retailPrice,
            entity.basePrice,
            entity.discountedPrice,
            entity.discountRate,
            "",
            entity.isSoldOut,
            entity.mainImageUrl,
            entity.name,
            entity.no.toLong(),
            entity.reviewCount,
            entity.adultVerificationFailed
        )
    }
}