package com.example.commerceapp.domain.usecases.cart

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.repository.CartRepository
import com.example.commerceapp.domain.repository.UserRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

data class GetCartListUseCase(
    val repository: CartRepository,
    private val loginExceptionHandler: ErrorHandler
) : BaseFlowUseCase<List<ProductPreview>>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<List<ProductPreview>>> =
        repository.getCartList(parameters).mapToResultEntity(loginExceptionHandler)

}