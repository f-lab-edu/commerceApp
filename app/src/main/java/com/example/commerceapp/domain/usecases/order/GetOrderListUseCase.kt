package com.example.commerceapp.domain.usecases.order

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.OrderPreview
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.OrderRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

data class GetOrderListUseCase(
    private val repository: OrderRepository,
    private val loginExceptionHandler: ErrorHandler
) : BaseFlowUseCase<List<OrderPreview>>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<List<OrderPreview>>> =
        repository.getOrderList(parameters).mapToResultEntity(loginExceptionHandler)

}