package com.example.commerceapp.data.remote.model.product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Brand(
    val _id: String? = null,
    val name: String? = null
)