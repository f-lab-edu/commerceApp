package com.example.commerceapp.data.remote.response

import com.example.commerceapp.data.remote.model.Item
import com.example.commerceapp.data.remote.model.product.Product
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductSearchResponse(
    @Json(name = "items") val items: List<Product>,
    @Json(name = "start") val start: Int, // 검색 시작 위치
)