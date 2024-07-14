package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.DataError
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.InvalidDataException
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.common.request.ProductSearchParam
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.model.product.ProductHandler
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository,
    private val errorHandler: ProductHandler
) {
    private val RELATED_PRODUCT_SIZE = 5

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun invoke(id: String): Flow<ResultEntity<Product, Error>> =
        repository.getProduct(id).flatMapLatest { product ->
            checkProductValidation(product)
            searchRelatedProductIfKeywordIsValid(product)
        }.mapToResultEntity(errorHandler)

    private fun checkProductValidation(product: Product) {
        if (product.name.isEmpty() || product.name.isBlank()) throw InvalidDataException(DataError.VALIDATION.MISSING_VALUE)
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
}