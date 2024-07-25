package com.example.commerceapp.data.remote.model.mapper

import com.example.commerceapp.data.remote.model.ProductDto
import com.example.commerceapp.domain.model.product.Product

class ProductMapper {
    fun mapToProduct(dto: ProductDto): Product {
        return Product(
            dto.retailPrice ?: 0,
            dto.basePrice ?: 0,
            dto.discountedPrice ?: 0,
            dto.discountRate ?: 0.0,
            dto.expirationDate ?: "",
            dto.isSoldOut ?: false,
            dto.mainImageUrl ?: "",
            dto.name.toString() ?: "",
            dto.no.toString() ?: "",
            dto.reviewCount ?: 0,
            dto.shortDescription ?: "",
            dto.sellerName ?: "",
            dto.adultVerificationFailed ?: false,
            dto.isThirdPart ?: false,
            dto.productVerticalSmallUrl ?: "",
            dto.masterProductCode ?: "",
            dto.masterProductName ?: "",
            dto.dealProducts ?: emptyList(),
            emptyList()
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
            product.no.toLong(),
            product.reviewCount,
            product.shortDescription,
            product.sellerName,
            product.adultVerificationFailed,
            product.isThirdPart,
            product.productVerticalSmallUrl,
            product.masterProductCode,
            product.masterProductName,
            emptyList()
        )
    }
}