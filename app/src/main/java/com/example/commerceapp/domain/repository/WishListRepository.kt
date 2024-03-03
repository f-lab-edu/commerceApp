package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.product.ProductPreview
import kotlinx.coroutines.flow.Flow

interface WishListRepository {
    fun getCartList(requestParam: RequestParam): Flow<List<ProductPreview>>
    fun addToCart(requestParam: RequestParam): Flow<String>
    fun removeFromCart(requestParam: RequestParam): Flow<String>
}