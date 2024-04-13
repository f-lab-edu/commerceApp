package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.product.ProductHandler
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.repository.ProductRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository<RequestParam>,
    private val errorHandler: ProductHandler
) : BaseFlowUseCase<List<ProductPreview>>() {
    override suspend operator fun invoke(parameters: RequestParam): Flow<ResultEntity<List<ProductPreview>>> =
        repository.searchProduct(parameters).mapToResultEntity(errorHandler)
}