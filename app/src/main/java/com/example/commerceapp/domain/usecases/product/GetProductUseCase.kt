package com.example.commerceapp.domain.usecases.product

import android.os.Build
import androidx.annotation.RequiresExtension
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
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun invoke(id: String): Flow<ResultEntity<Product>> =
        repository.getProduct(id).mapToResultEntity(errorHandler)
}