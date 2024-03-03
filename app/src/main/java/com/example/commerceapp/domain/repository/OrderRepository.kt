package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.Order
import com.example.commerceapp.domain.model.common.RequestParam
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getOrderList(requestParam: RequestParam): Flow<List<Order>>
    fun cancelOrder(requestParam: RequestParam): Flow<String>
    fun order(requestParam: RequestParam): Flow<Order>
}