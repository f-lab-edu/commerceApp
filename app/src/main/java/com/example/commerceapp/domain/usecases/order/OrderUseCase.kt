package com.example.commerceapp.domain.usecases.order

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.Order
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.OrderRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

class OrderUseCase(
    val repository: OrderRepository,
    private val loginExceptionHandler: ErrorHandler
) : BaseFlowUseCase<Order>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<Order>> =
        repository.order(parameters).mapToResultEntity(loginExceptionHandler)

}