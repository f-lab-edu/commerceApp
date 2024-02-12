package com.example.commerceapp.data.remote.model.product

import com.example.commerceapp.domain.enntity.product.ProductEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "_id") val id: String? = null,
//    @Json(name = "attributes") val attributes: List<Attribute>?=null,
    @Json(name = "averageReviewScore") val averageReviewScore: Double? = null,
    @Json(name = "best") val isBest: Boolean? = null,
//    @Json(name = "brand") val brand: Brand?=null,
//    @Json(name = "images") val images: List<String>?=null,
    @Json(name = "isNewItem") val isNewItem: Boolean? = null,
    @Json(name = "mobileDiscountPrice") val discountPrice: Int? = null,
    @Json(name = "mobileDiscountRate") val discountRate: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "productNo") val productNo: String? = null,
    @Json(name = "salePrice") val salePrice: Int? = null,
) : Serializable {
    fun mapToProductEntity(): ProductEntity {
        return ProductEntity(
            id = id,
//            attributes = attributes,
            averageReviewScore = averageReviewScore,
            isBest = isBest,
//            brand = brand,
//            images = images,
            isNewItem = isNewItem,
            discountPrice = discountPrice,
            discountRate = discountRate,
            name = name,
            productNo = productNo,
            salePrice = salePrice
        )
    }
}