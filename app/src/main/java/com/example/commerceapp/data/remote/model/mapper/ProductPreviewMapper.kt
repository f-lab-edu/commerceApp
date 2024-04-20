package com.example.commerceapp.data.remote.model.mapper

import com.example.commerceapp.data.remote.model.ProductPreviewDto
import com.example.commerceapp.domain.model.product.ProductPreview

object ProductPreviewMapper {
    fun mapToEntity(dto: ProductPreviewDto): ProductPreview {
        return ProductPreview(
            dto.retailPrice,
            dto.basePrice,
            dto.discountedPrice,
            dto.discountRate,
            dto.expirationDate,
            dto.isSoldOut,
            dto.mainImageUrl,
            dto.name,
            dto.no,
            dto.reviewCount,
            dto.adultVerificationFailed
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
            entity.no,
            entity.reviewCount,
            entity.adultVerificationFailed
        )
    }
}