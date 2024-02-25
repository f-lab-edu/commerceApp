package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.Result
import com.example.commerceapp.domain.Result.Success
import com.example.commerceapp.domain.enntity.product.ProductDetailEntity
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    val repository: ProductRepository
) {
    operator fun invoke(id: String): Flow<Result<ProductDetailEntity>> =
        repository.getProductDetail(id).map(::Success)

}