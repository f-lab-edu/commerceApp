package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.Order
import com.example.commerceapp.domain.model.OrderPreview
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.response.Response
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getOrderList(requestParam: RequestParam): Flow<List<OrderPreview>>
    fun cancelOrder(requestParam: RequestParam): Flow<Response>
    fun order(requestParam: RequestParam): Flow<Order>
}