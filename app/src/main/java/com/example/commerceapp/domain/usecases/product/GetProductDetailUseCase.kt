package com.example.commerceapp.domain.usecases.product

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.commerceapp.domain.extension.toErrorType
import com.example.commerceapp.domain.model.ErrorHandler
import com.example.commerceapp.domain.model.Product.ProductDetail
import com.example.commerceapp.domain.model.RequestParam
import com.example.commerceapp.domain.model.ResultEntity
import com.example.commerceapp.domain.repository.ProductRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    val repository: ProductRepository
) : BaseFlowUseCase<ProductDetail>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<ProductDetail>> =
        repository.getProductDetail(parameters).mapToResultEntity<ProductDetail>(ProductHandler())

    class ProductHandler : ErrorHandler {
        @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
        override fun <ProductDetail> handle(throwable: Throwable): ResultEntity.Error<ProductDetail> {
            return ResultEntity.Error(
                error = throwable.toErrorType(),
                message = throwable.message ?: throwable.stackTraceToString()
            )
        }
    }

    fun <T> Flow<T>.mapToResultEntity(handler: ErrorHandler): Flow<ResultEntity<T>> =
        map {
            try {
                ResultEntity.Success(it)
            } catch (t: Throwable) {
                handler.handle(t)
            }
        }

}