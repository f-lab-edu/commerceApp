package com.example.commerceapp.data.remote.model.product

import com.squareup.moshi.JsonClass

//package com.example.commerceapp.data.remote.model
//
@JsonClass(generateAdapter = true)
data class Image(
    val fileSize: Int?=null,
    val height: Int?=null,
    val imageClassType: String?=null,
    val imageUrl: String?=null,
    val representativeImage: Boolean?=null,
    val sortOrder: Int?=null,
    val width: Int?=null
)