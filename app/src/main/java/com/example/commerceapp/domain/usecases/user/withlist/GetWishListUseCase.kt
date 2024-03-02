package com.example.commerceapp.domain.usecases.user.withlist

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.repository.UserRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

data class GetWishListUseCase(
    val repository: UserRepository,
    private val loginExceptionHandler: ErrorHandler
) : BaseFlowUseCase<List<Product>>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<List<Product>>> =
        repository.getWishList(parameters).mapToResultEntity(loginExceptionHandler)

}