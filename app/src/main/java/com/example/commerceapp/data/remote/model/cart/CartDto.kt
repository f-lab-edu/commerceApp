package com.example.commerceapp.data.remote.model.cart

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartDto(
    val no: String = "",
    val amount: Int = 0
)