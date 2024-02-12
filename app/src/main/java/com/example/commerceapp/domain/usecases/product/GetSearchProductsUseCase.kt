package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.Result
import com.example.commerceapp.domain.Result.Success
import com.example.commerceapp.data.remote.request.ItemSearchRequest
import com.example.commerceapp.domain.enntity.product.ProductEntity
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchProductsUseCase @Inject constructor(
    val repository: ProductRepository
) {
    operator fun invoke(request: ItemSearchRequest): Flow<Result<List<ProductEntity>>> =
        repository.searchProduct(request.requestToMap()).map {
            Result.Success(it.items.map { item -> item.mapToProductEntity() }.toList())
        }
}