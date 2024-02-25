package com.example.commerceapp.domain.usecases.product

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.commerceapp.domain.extension.toErrorType
import com.example.commerceapp.domain.model.ErrorHandler
import com.example.commerceapp.domain.model.Product.Product
import com.example.commerceapp.domain.model.RequestParam
import com.example.commerceapp.domain.model.ResultEntity
import com.example.commerceapp.domain.repository.ProductRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchProductsUseCase @Inject constructor(
    val repository: ProductRepository
) : BaseFlowUseCase<List<Product>>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend operator fun invoke(request: RequestParam): Flow<ResultEntity<List<Product>>> =
        repository.searchProduct(request).mapToResultEntity<List<Product>>(ProductHandler())

    class ProductHandler : ErrorHandler {
        @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
        override fun <T> handle(throwable: Throwable): ResultEntity.Error<T> {
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