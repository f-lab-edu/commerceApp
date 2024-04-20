package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.common.request.ProductSearchParam
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.model.product.ProductPreviewHandler
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProducts @Inject constructor(
    private val repository: ProductRepository,
    private val errorHandler: ProductPreviewHandler
) {
    suspend operator fun invoke(
        searchParam: ProductSearchParam
    ): Flow<ResultEntity<List<ProductPreview>>> =
        repository.searchProduct(searchParam).mapToResultEntity(errorHandler)
}