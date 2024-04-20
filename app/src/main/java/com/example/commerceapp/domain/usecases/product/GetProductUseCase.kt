package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.model.product.ProductHandler
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository,
    private val errorHandler: ProductHandler
) {
    suspend fun invoke(id: String): Flow<ResultEntity<Product>> =
        repository.getProduct(id).mapToResultEntity(errorHandler)
}