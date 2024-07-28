package com.example.commerceapp.domain.usecases.order

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.Order
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class OrderUseCase(
    private val repository: OrderRepository,
    private val loginExceptionHandler: ErrorHandler
) {
    suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<Order,Error>> =
        repository.order(parameters).mapToResultEntity(loginExceptionHandler)

}