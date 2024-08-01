package com.example.commerceapp.domain.model.cart

import com.example.commerceapp.domain.model.product.ProductPreview

data class CartItem(
    val productPreview: ProductPreview,
    val amount:Int
)