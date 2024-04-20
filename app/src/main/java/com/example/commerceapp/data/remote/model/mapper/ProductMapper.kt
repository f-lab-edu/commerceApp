package com.example.commerceapp.data.remote.model.mapper

import com.example.commerceapp.data.remote.model.ProductDto
import com.example.commerceapp.domain.model.product.Product

object ProductMapper {
    fun mapToProduct(dto: ProductDto): Product {
        return Product(
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
            dto.shortDescription,
            dto.sellerName,
            dto.adultVerificationFailed,
            dto.isThirdPart,
            dto.productVerticalSmallUrl,
            dto.masterProductCode,
            dto.masterProductName,
            dto.dealProducts
        )
    }

    fun mapToDto(product: Product): ProductDto {
        return ProductDto(
            product.retailPrice,
            product.basePrice,
            product.discountedPrice,
            product.discountRate,
            product.expirationDate,
            product.isSoldOut,
            product.mainImageUrl,
            product.name,
            product.no,
            product.reviewCount,
            product.shortDescription,
            product.sellerName,
            product.adultVerificationFailed,
            product.isThirdPart,
            product.productVerticalSmallUrl,
            product.masterProductCode,
            product.masterProductName,
            product.dealProducts
        )
    }
}