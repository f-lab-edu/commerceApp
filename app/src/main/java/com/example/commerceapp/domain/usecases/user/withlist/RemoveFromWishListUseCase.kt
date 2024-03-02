package com.example.commerceapp.domain.usecases.user.withlist

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.UserRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

data class RemoveFromWishListUseCase(
    val repository: UserRepository,
    private val loginExceptionHandler: ErrorHandler
) : BaseFlowUseCase<String>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<String>> =
        repository.removeFromWishList(parameters).mapToResultEntity(loginExceptionHandler)
}