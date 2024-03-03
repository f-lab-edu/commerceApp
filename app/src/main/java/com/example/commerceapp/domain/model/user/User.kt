package com.example.commerceapp.domain.model.user

import com.example.commerceapp.domain.model.OrderPreview
import com.example.commerceapp.domain.model.product.ProductPreview

data class User(
    val id: String,
    val name: String,
    val age: Int,
    val email: String,
    val phoneNo: String,
    val addressee: List<Addressee>,
    val orderList: List<OrderPreview>,
    val wishList: List<ProductPreview>
)