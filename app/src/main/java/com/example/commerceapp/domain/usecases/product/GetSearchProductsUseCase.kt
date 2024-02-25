package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.Result
import com.example.commerceapp.domain.Result.Success
import com.example.commerceapp.data.remote.request.ProductSearchRequest
import com.example.commerceapp.domain.enntity.product.ProductEntity
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchProductsUseCase @Inject constructor(
    val repository: ProductRepository
) {
    operator fun invoke(request: ProductSearchRequest): Flow<Result<List<ProductEntity>>> =
        repository.searchProduct(request.requestToMap()).map { it.items }.map(::Success)
}