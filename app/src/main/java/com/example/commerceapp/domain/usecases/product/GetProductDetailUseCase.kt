package com.example.commerceapp.domain.usecases.product

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.product.ProductDetail
import com.example.commerceapp.domain.model.product.ProductHandler
import com.example.commerceapp.domain.repository.ProductRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    val repository: ProductRepository,
    private val errorHandler: ProductHandler
) : BaseFlowUseCase<ProductDetail>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<ProductDetail>> =
        repository.getProductDetail(parameters).mapToResultEntity(errorHandler)
}