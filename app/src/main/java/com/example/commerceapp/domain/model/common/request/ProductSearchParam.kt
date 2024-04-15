package com.example.commerceapp.domain.model.common.request

data class ProductSearchParam(
    val keyword: String = "",
    val page: Int = 1,
    val pagePerSize: Int = 20
)
