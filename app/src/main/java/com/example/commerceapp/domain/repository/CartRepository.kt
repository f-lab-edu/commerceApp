package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.response.Response
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartList(requestParam: RequestParam): Flow<List<ProductPreview>>
    fun addToCart(requestParam: RequestParam): Flow<Response>
    fun removeFromCart(requestParam: RequestParam): Flow<Response>
}