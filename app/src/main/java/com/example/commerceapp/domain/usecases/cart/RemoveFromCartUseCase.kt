package com.example.commerceapp.domain.usecases.cart

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.CartRepository
import com.example.commerceapp.domain.response.Response
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

data class RemoveFromCartUseCase(
    val repository: CartRepository,
    private val loginExceptionHandler: ErrorHandler
) : BaseFlowUseCase<Response>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<Response>> =
        repository.removeFromCart(parameters).mapToResultEntity(loginExceptionHandler)

}