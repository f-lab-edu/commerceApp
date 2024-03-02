package com.example.commerceapp.domain.model.product

import com.example.commerceapp.domain.extension.toErrorType
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.ResultEntity

class ProductHandler : ErrorHandler {
    override fun <ProductDetail> handle(throwable: Throwable): ResultEntity.Error<ProductDetail> {
        return ResultEntity.Error(
            error = throwable.toErrorType(),
            message = throwable.message ?: throwable.stackTraceToString()
        )
    }
}