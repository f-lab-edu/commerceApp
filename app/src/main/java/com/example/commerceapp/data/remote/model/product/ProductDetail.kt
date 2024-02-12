package com.example.commerceapp.data.remote.model.product

import android.media.Image
import com.example.commerceapp.domain.enntity.product.ProductDetailEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ProductDetail(
    @Json(name = "_id") val id: String? = null,
//    @Json(name = "attributes") val attributes: List<Attribute>? = null,
    @Json(name = "averageReviewScore") val averageReviewScore: Double? = null,
    @Json(name = "best") val isBest: Boolean? = null,
//    @Json(name = "brand") val brand: Brand? = null,
    @Json(name = "contentText") val contentText: String? = null,
//    @Json(name = "images") val images: List<Image>? = null,
    @Json(name = "isNewItem") val isNewItem: Boolean? = null, // 신? = null,
    @Json(name = "menus") val menus: List<String>? = null,
    @Json(name = "mobileDiscountPrice") val discountPrice: Int? = null,
    @Json(name = "mobileDiscountRate") val discountRate: Int? = null,
    @Json(name = "modelNumber") val modelNumber: String? = null,
    @Json(name = "name") val name: String? = null,
//    @Json(name = "naverShoppingCategory") val naverShoppingCategory: NaverShoppingCategory? = null,
    @Json(name = "npay") val npay: Boolean? = null,
    @Json(name = "nvMid") val nvMid: Long? = null,
    @Json(name = "productNo") val productNo: String? = null,
    @Json(name = "salePrice") val salePrice: Int? = null,
    @Json(name = "soldout") val soldout: Boolean? = null,
    @Json(name = "standardColors") val standardColors: List<Any>? = null,
    @Json(name = "standardSizes") val standardSizes: List<Any>? = null,
//    @Json(name = "tags") val tags: List<Tag>? = null,
    @Json(name = "totalReviewCount") val totalReviewCount: Int? = null,
    @Json(name = "totalSaleCount") val totalSaleCount: Int? = null,
    @Json(name = "unionCategories") val unionCategories: List<Any>? = null
//    @Json(name = "channel") val channel: Channel,
//    @Json(name = "createdAt") val createdAt: String,
//    @Json(name = "deliveryAttributeType") val deliveryAttributeType: String, // 당일배송
//    @Json(name = "deliveryFeeType") val deliveryFeeType: String, // 배송비 부과 타입
//    @Json(name = "exposure") val exposure: Boolean,
//    @Json(name = "freeReturnInsurance") val freeReturnInsurance: Boolean,
//    @Json(name = "fulfillment") val fulfillment: Fulfillment,
//    @Json(name = "guaranteeTypes") val guaranteeTypes: List<String>,
//    @Json(name = "inspectedAt") val inspectedAt: String,
//    @Json(name = "inspectionStatus") val inspectionStatus: String,
//    @Json(name = "isRepresentativeGroupProduct") val isRepresentativeGroupProduct: Boolean, // ??
//    @Json(name = "martUpdatedAt") val martUpdatedAt: String,
//    @Json(name = "pcDiscountPrice") val pcDiscountPrice: Int,
//    @Json(name = "pcDiscountRate") val pcDiscountRate: Int,
//    @Json(name = "popularScore") val popularScore: Double,
//    @Json(name = "productDeliveryInfo") val productDeliveryInfo: ProductDeliveryInfo,
//    @Json(name = "recentSaleCount") val recentSaleCount: Int,
//    @Json(name = "reorderCount") val reorderCount: Int,
//    @Json(name = "satisfactionPercent") val satisfactionPercent: Int,
//    @Json(name = "storeKeepExclusiveProduct") val storeKeepExclusiveProduct: Boolean,
//    @Json(name = "talkPay") val talkPay: Boolean,
//    @Json(name = "updatedAt") val updatedAt: String,
//    @Json(name = "version") val version: Long,
//    @Json(name = "viewCountFromWindowApi") val viewCountFromWindowApi: Int
) : Serializable {

    fun mapToProductDetailEntity(): ProductDetailEntity {
        return ProductDetailEntity(
            id = id,
            averageReviewScore = averageReviewScore,
            isBest = isBest,
//            brand = brand,
            contentText = contentText,
//            images = images?.map { it.planes.toString() },
            isNewItem = isNewItem,
            menus = menus,
            discountPrice = discountPrice,
            discountRate = discountRate,
            modelNumber = modelNumber,
            name = name,
            npay = npay,
            nvMid = nvMid,
            productNo = productNo,
            salePrice = salePrice,
            soldout = soldout,
            standardColors = standardColors,
            standardSizes = standardSizes,
            totalReviewCount = totalReviewCount,
            totalSaleCount = totalSaleCount,
            unionCategories = unionCategories
        )
    }
}