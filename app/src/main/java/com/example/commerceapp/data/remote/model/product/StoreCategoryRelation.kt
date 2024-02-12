package com.example.commerceapp.data.remote.model.product

data class StoreCategoryRelation(
    val channelId: String,
    val frontExposureName: String,
    val inspectionStatus: String,
    val storeCategory: List<String>,
    val subVertical: String,
    val vertical: String
)