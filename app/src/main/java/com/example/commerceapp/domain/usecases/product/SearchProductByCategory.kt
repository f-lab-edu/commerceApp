package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.product.ProductHandler
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductByCategory @Inject constructor(
    private val repository: ProductRepository,
    private val errorHandler: ProductHandler
) {
    suspend operator fun invoke(categoryName: String): Flow<ResultEntity<List<ProductPreview>>> =
        repository.productSearchByCategory(categoryName).mapToResultEntity(errorHandler)
}