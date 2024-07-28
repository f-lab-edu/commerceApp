package com.example.commerceapp.domain.usecases.cart

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.DataErrorHandler
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

data class RemoveFromCartUseCase(
    private val repository: CartRepository<RequestParam>,
    private val dataErrorHandler: DataErrorHandler
) {
    suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<String, Error>> =
        repository.removeFromCart(parameters).mapToResultEntity(dataErrorHandler)

}