package com.example.commerceapp.domain.usecases.cart

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.CartRepository
import com.example.commerceapp.domain.response.Response
import kotlinx.coroutines.flow.Flow

data class RemoveFromCartUseCase(
    private val repository: CartRepository,
    private val loginExceptionHandler: ErrorHandler
){
     suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<Response, Error>> =
        repository.removeFromCart(parameters).mapToResultEntity(loginExceptionHandler)

}