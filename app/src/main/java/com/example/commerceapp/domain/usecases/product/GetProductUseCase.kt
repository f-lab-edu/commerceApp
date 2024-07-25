package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.DataError
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.common.request.ProductSearchParam
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.model.product.ProductHandler
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository,
    private val errorHandler: ProductHandler
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun invoke(id: String) =
        repository.getProduct(id).flatMapConcat { product ->
            if (isNotValid(product)) {
                flow { ResultEntity.Error<Product, DataError>(error = DataError.VALIDATION.MISSING_VALUE) }
            } else {
                searchRelatedProductIfKeywordIsValid(product).mapToResultEntity(errorHandler)
            }
        }

    private fun isNotValid(product: Product): Boolean {
        return (product.name.isEmpty() || product.name.isBlank())
    }

    private suspend fun searchRelatedProductIfKeywordIsValid(product: Product): Flow<Product> {
        val lastRightBracketIndex = product.name.lastIndexOf('[')
        val extractedText =
            product.name.substring(lastRightBracketIndex + 1, product.name.indexOf(']'))
        var productDetail = product

        return repository.searchProduct(
            ProductSearchParam(
                keyword = extractedText,
                pagePerSize = RELATED_PRODUCT_SIZE
            )
        )
            .map {
                if (it.isNotEmpty()) productDetail = productDetail.copy(relatedProducts = it)
                productDetail
            }
    }

    companion object {
        const val RELATED_PRODUCT_SIZE = 5
    }
}