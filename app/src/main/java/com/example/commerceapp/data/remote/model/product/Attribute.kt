package com.example.commerceapp.data.remote.model.product

import com.example.commerceapp.data.remote.model.product.Value
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attribute(
    val _id: String?=null,
    val name: String?=null,
    val values: List<Value>?=null
)