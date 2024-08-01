package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.common.RequestParam
import kotlinx.coroutines.flow.Flow

interface CartRepository<out T : RequestParam> {
    suspend fun <T> getCartList(requestParam: T): Flow<HashMap<String, Long>>
    suspend fun addToCart(requestParam: RequestParam): Flow<String>
    suspend fun removeFromCart(requestParam: RequestParam): Flow<String>

    interface CartRequestParam : RequestParam
}