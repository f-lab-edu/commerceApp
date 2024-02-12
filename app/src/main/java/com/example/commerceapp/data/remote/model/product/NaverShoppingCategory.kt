package com.example.commerceapp.data.remote.model.product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NaverShoppingCategory(
    val _id: String? = null,
    val name: String? = null,
    val wholeId: String? = null,
    val wholeIds: List<String>? = null,
    val wholeName: String? = null,
)