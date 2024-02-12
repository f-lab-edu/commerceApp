package com.example.commerceapp.data.remote.model.product

import com.example.commerceapp.data.remote.model.product.LogoImage
import com.example.commerceapp.data.remote.model.product.RepresentativeImage
import com.example.commerceapp.data.remote.model.product.StoreCategoryRelation

data class Channel(
    val _id: String,
    val additionalImages: List<Any>,
    val alias: String,
    val bestShopYn: Boolean,
    val channelUid: String,
    val createdAt: String,
    val handleBrandNames: List<Any>,
    val hasHighRatingReview: Boolean,
    val initialIndexTypes: List<Any>,
    val initialTypes: List<Any>,
    val initialWords: List<Any>,
    val inquiryUseYn: Boolean,
    val inspectionBypassYn: Boolean,
    val inspectionStatus: String,
    val lastModifiedTimestamp: Int,
    val logoImage: LogoImage,
    val name: String,
    val representBrands: List<Any>,
    val representativeImage: RepresentativeImage,
    val storeCategory: List<String>,
    val storeCategoryRelations: List<StoreCategoryRelation>,
    val subVertical: String,
    val talkAccountId: String,
    val talkExposedYn: Boolean,
    val testPayYn: Boolean,
    val updatedAt: String,
    val vertical: String
)