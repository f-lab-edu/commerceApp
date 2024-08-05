package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.cart.Cart
import com.example.commerceapp.domain.model.common.RequestParam
import kotlinx.coroutines.flow.Flow

interface CartRepository<out T : CartRepository.CartRequestParam> {
    suspend fun <T> getCartList(requestParam: T): Flow<List<Cart>>
    suspend fun <T> addToCart(requestParam: T): Flow<String>
    suspend fun <T> removeFromCart(requestParam: T): Flow<String>

    interface CartRequestParam : RequestParam
}