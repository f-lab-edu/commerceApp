package com.example.commerceapp.data.remote.model.mapper

import com.example.commerceapp.domain.model.CartItem
import com.example.commerceapp.domain.model.product.ProductPreview

class CartItemMapper {
    fun mapToCartItem(productPreview: ProductPreview, amount: Int): CartItem {
        return CartItem(
            productPreview,
            amount
        )
    }
}